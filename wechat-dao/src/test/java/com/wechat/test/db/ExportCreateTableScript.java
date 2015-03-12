package com.wechat.test.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.internal.util.ConfigHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;
import org.junit.Test;
import org.w3c.dom.Document;

import com.site.util.XmlObject;
import com.web.dao.db.ConfigurationUtil;

public class ExportCreateTableScript {

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws Exception {
		String path = ClassLoader.getSystemResource("").getPath();
		path = path.replace("target/test-classes/", "src/test/resources/wechat.sql");
		System.out.println(path);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		Document doc = getExportScriptConfig();
		Configuration configuration = ConfigurationUtil.buildConfiguration(doc);
		ServiceRegistry serviceRegistry = ConfigurationUtil.buildServiceRegistry(configuration);
		JdbcServices jdbcServices = serviceRegistry.getService(JdbcServices.class);
		Dialect dialect = jdbcServices.getDialect();
		Connection connection = jdbcServices.getConnectionProvider().getConnection();

		DatabaseMetadata meta = new DatabaseMetadata(connection, dialect, configuration);
		List<SchemaUpdateScript> scripts = configuration.generateSchemaUpdateScriptList(dialect, meta);
		for (SchemaUpdateScript script : scripts) {
			System.out.println(script.getScript());
			bw.write(script.getScript());
			bw.newLine();
		}
		bw.close();
	}

	private static InputStream trim(String path) throws IOException {
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

	private static Document getExportScriptConfig() throws Exception {
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String user = "root";
		String pwd = "root";
		InputStream is = trim("hibernate.cfg.xml");
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
