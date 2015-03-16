package com.wechat.dao.db;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.w3c.dom.Document;

import com.wechat.common.util.ClassUtil;
import com.wechat.common.util.Util;
import com.wechat.common.util.XmlObject;

public class ConfigurationUtil {

	private static final String defaultPackage = "com.wechat.dao.entity";
	private static Logger logger = Logger.getLogger(ConfigurationUtil.class);

	public static Configuration buildDefaultConfiguration() {
		BuildOption option = BuildOption.Online;
		if (Util.isDevelopEnvironment()) {
			logger.info("Development Environment");
			option = BuildOption.Development;
		}
		return buildConfiguration(option);
	}

	public static Configuration buildConfiguration(BuildOption option) {
		XmlObject conf = getConfObject(option);
		Configuration configuration = new Configuration();
		Document doc = XmlObject.Convert.toDocument(conf);
		configuration.configure(doc);
		return configuration;
	}

	private static XmlObject getConfObject(BuildOption option) {
		try {
			InputStream is = ClassLoader.getSystemResourceAsStream(option.getPath());
			InputStream trimIs = trim(is);
			XmlObject conf = XmlObject.readFromStream(trimIs);
			is.close();
			trimIs.close();
			forSepcOption(conf, option);
			addEntityInfo(conf);
			logger.info(conf.toXmlString());
			return conf;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void addEntityInfo(XmlObject conf) {
		List<Class<?>> classNames = ClassUtil.getClasses(defaultPackage);
		for (Class<?> c : classNames) {
			XmlObject mapping = conf.get("session-factory").add("mapping");
			mapping.setAttribute("class", c.getName());
		}
	}

	/**
	 * @param is
	 * 
	 *            去掉hibernate xml第一行的DOC
	 * @return
	 */
	private static InputStream trim(InputStream is) {
		try {
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
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void forSepcOption(XmlObject conf, BuildOption option) {
		String url = "jdbc:mysql://127.0.0.1:3306/" + option.getDb();
		String user = option.getUser();
		String pwd = option.getPwd();
		XmlObject sessionFactory = conf.get("session-factory");
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
	}

	public static ServiceRegistry buildServiceRegistry(Configuration configuration) {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		return serviceRegistry;
	}

}
