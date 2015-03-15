package com.wechat.dao.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;

import com.wechat.dao.db.BuildOption;
import com.wechat.dao.db.ConfigurationUtil;

public class DbHandler {

	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private JdbcServices jdbcServices;
	private Connection conn;

	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("deprecation")
	public DbHandler() {
		configuration = ConfigurationUtil.buildConfiguration(BuildOption.Test);
		serviceRegistry = ConfigurationUtil.buildServiceRegistry(configuration);
		jdbcServices = serviceRegistry.getService(JdbcServices.class);
		try {
			conn = jdbcServices.getConnectionProvider().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<SchemaUpdateScript> generateSchemaUpdateScriptList() {
		try {
			Dialect dialect = jdbcServices.getDialect();
			DatabaseMetadata meta = new DatabaseMetadata(conn, dialect, configuration);
			List<SchemaUpdateScript> scripts = configuration.generateSchemaUpdateScriptList(dialect, meta);
			return scripts;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void dropTables() {
		boolean finish = false;
		while (!finish) {
			finish = true;
			List<String> tables = getTables();
			for (String table : tables) {
				String sql = String.format("drop table %s", table);
				if (!execute(sql)) {
					finish = false;
				}
			}
		}
	}

	public List<String> getTables() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, "%", "%", null);
			while (rs.next()) {
				list.add(rs.getString("TABLE_NAME"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public boolean execute(String sql) {
		System.out.println(sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			stmt.execute(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			if (needCatchException(e)) {
				System.out.println(String.format("Execute Sql Fail: %s (%s)", sql, e.getLocalizedMessage()));
				return false;
			}
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public boolean execute(InputStream is) {
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				execute(line);
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean needCatchException(SQLException e) {
		return e.getSQLState().equals("23000") || e.getSQLState().equals("42000");
	}

	public boolean validate(String orig, String update) {
		return false;
	}

}
