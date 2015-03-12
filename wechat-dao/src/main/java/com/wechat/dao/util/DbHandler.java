package com.wechat.dao.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import org.hibernate.internal.util.ConfigHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;
import org.w3c.dom.Document;

import com.site.util.XmlObject;
import com.wechat.dao.db.ConfigurationUtil;

public class DbHandler {

	private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";
	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private JdbcServices jdbcServices;

	public DbHandler(String db, String user, String pwd) throws Exception {
		Document doc = getSpecConfig(db, user, pwd);
		configuration = ConfigurationUtil.buildConfiguration(doc);
		serviceRegistry = ConfigurationUtil.buildServiceRegistry(configuration);
		jdbcServices = serviceRegistry.getService(JdbcServices.class);
	}

	@SuppressWarnings("deprecation")
	public List<SchemaUpdateScript> generateSchemaUpdateScriptList() throws Exception {
		JdbcServices jdbcServices = serviceRegistry.getService(JdbcServices.class);
		Dialect dialect = jdbcServices.getDialect();
		Connection connection = jdbcServices.getConnectionProvider().getConnection();
		DatabaseMetadata meta = new DatabaseMetadata(connection, dialect, configuration);
		List<SchemaUpdateScript> scripts = configuration.generateSchemaUpdateScriptList(dialect, meta);
		return scripts;
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

	@SuppressWarnings("deprecation")
	public List<String> getTables() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			DatabaseMetaData md = jdbcServices.getConnectionProvider().getConnection().getMetaData();
			ResultSet rs = md.getTables(null, "%", "%", null);
			while (rs.next()) {
				list.add(rs.getString("TABLE_NAME"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	public boolean execute(String sql) {
		Statement stmt;
		try {
			stmt = jdbcServices.getConnectionProvider().getConnection().createStatement();
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
				if (!execute(line))
					return false;
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

	private InputStream trim(String path) throws IOException {
		InputStream is = ConfigHelper.getResourceAsStream(path);
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		boolean trim = false;
		while ((line = br.readLine()) != null) {
			if (trim && line.trim().endsWith(">")) {
				trim = false;
				continue;
			} else if (trim) {
				continue;
			} else if (line.trim().startsWith("<!DOCTYPE")) {
				trim = true;
				continue;
			} else {
				sb.append(line);
			}
		}
		ByteArrayInputStream ret = new ByteArrayInputStream(sb.toString().getBytes());
		return ret;
	}

	private Document getSpecConfig(String db, String user, String pwd) throws Exception {
		String url = "jdbc:mysql://127.0.0.1:3306/" + db;
		InputStream is = trim(HIBERNATE_CFG_XML);
		XmlObject obj = XmlObject.readFromStream(is);
		XmlObject sessionFactory = obj.get("session-factory");
		int length = sessionFactory.getLength("property");
		for (int i = 0; i < length; i++) {
			XmlObject property = sessionFactory.get("property", i);
			String name = property.getAttribute("name");
			if (name.equals("connection.url")) {
				property.setText(url);
			} else if (name.equals("connection.username")) {
				property.setText(user);
			} else if (name.equals("connection.password")) {
				property.setText(pwd);
			}
		}
		return XmlObject.Convert.toDocument(obj);
	}
}
