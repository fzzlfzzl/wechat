package com.wechat.dao.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	// private static Logger logger = Logger.getLogger(HibernateUtil.class);

	private static SessionFactory sessionFactory = null;// buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = ConfigurationUtil.buildDefaultConfiguration();
			ServiceRegistry serviceRegistry = ConfigurationUtil.buildServiceRegistry(configuration);
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Session openSession() {
		Session session = getSessionFactory().openSession();
		return session;
	}

	private static synchronized SessionFactory getSessionFactory() {
		if (null == sessionFactory) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

}
