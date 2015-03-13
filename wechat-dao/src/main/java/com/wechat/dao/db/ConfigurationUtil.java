package com.wechat.dao.db;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.ConfigHelper;
import org.hibernate.service.ServiceRegistry;
import org.w3c.dom.Document;

import com.site.util.Util;
import com.site.util.XmlObject;

public class ConfigurationUtil {

	private static Logger logger = Logger.getLogger(ConfigurationUtil.class);
	private static String defaultPath = "hibernate.cfg.xml";

	public static Configuration buildDefaultConfiguration() {
		try {
			Configuration configuration = new Configuration();
			if (Util.isDevelopEnvironment()) {
				logger.info("Development Environment");
				Document developConfig = getDevelopConfig();
				configuration.configure(developConfig);
			} else {
				logger.info("Online Environment");
				configuration.configure(defaultPath);
			}
			return configuration;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Configuration buildConfiguration(String path) {
		try {
			Configuration configuration = new Configuration();
			configuration.configure(path);
			return configuration;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Configuration buildConfiguration(Document doc) {
		try {
			Configuration configuration = new Configuration();
			configuration.configure(doc);
			return configuration;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ServiceRegistry buildServiceRegistry(Configuration configuration) {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		return serviceRegistry;
	}

	private static Document getDevelopConfig() throws Exception {
		String url = "jdbc:mysql://127.0.0.1:3306/wechat";
		String user = "root";
		String pwd = "root";
		InputStream is = trim(defaultPath);
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
		logger.info(obj.toXmlString());
		return XmlObject.Convert.toDocument(obj);
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
}
