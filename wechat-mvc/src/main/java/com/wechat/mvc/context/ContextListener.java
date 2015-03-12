package com.wechat.mvc.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.wechat.dao.db.HibernateUtil;

public class ContextListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("Server Stop................");
	}

	// 这个方法在Web应用服务做好接受请求的时候被调用。
	public void contextInitialized(ServletContextEvent event) {
		Session session = HibernateUtil.openSession();
		session.close();
		logger.info("Server Start................");
	}
}
