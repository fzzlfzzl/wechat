package com.test.dao;

import org.hibernate.Session;
import org.junit.Test;

import com.site.util.Util;
import com.wechat.dao.db.HibernateUtil;
import com.wechat.dao.entity.Orders;

public class OrderDaoTest {

	@Test
	public void orderDaoTest() {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Orders order = new Orders();
		order.setAddress("地址1");
		order.setName("名字1");
		order.setDishes("麻辣1");
		order.setTelephone("1234");
		order.setOpenid(Util.uuid());
		session.save(order);
		session.getTransaction().commit();
	}

}
