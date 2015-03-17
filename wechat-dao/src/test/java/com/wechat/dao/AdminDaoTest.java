package com.wechat.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.wechat.dao.db.HibernateUtil;
import com.wechat.dao.entity.Admin;
import com.wechat.dao.impl.AdminDao;

public class AdminDaoTest {

	@Test
	public void adminDaoTest() {
		Session session = HibernateUtil.openSession();
		AdminDao dao = new AdminDao(session);
		try {
			List<Admin> orig = dao.list();
			Admin admin = new Admin();
			admin.setName("test1");
			admin.setPassword("1");
			dao.save(admin);

			List<Admin> cur = dao.list();
			assertEquals(1, cur.size() - orig.size());

			Admin admin2 = dao.get("test1");
			assertEquals(admin.getId(), admin2.getId());
			dao.delete(admin);

			List<Admin> after = dao.list();
			assertEquals(after.size(), orig.size());
			admin2 = dao.get("test1");
			assertNull(admin2);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
