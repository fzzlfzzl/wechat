package com.wechat.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.junit.Test;

import com.wechat.common.util.Util;
import com.wechat.dao.db.HibernateUtil;
import com.wechat.dao.entity.Message;
import com.wechat.dao.entity.User;
import com.wechat.dao.impl.UserDao;
import com.wechat.dao.util.Common;

public class UserDaoTest {

	@Test
	public void userDaoTest() {
		Session session = HibernateUtil.openSession();
		UserDao dao = new UserDao(session);
		String openid1 = Util.uuid();
		String openid2 = Util.uuid();
		try {
			{
				// create and read
				User user = new User();
				user.setOpenId(openid1);
				dao.save(user);

				User user2 = dao.get(openid1);
				assertEquals(user, user2);
			}
			{
				// get nonexist
				User user = dao.get(openid2);
				assertNull(user);
			}
			{
				// update nonexist
				User user = new User();
				user.setOpenId(openid2);
				try {
					dao.update(user);
					fail();
				} catch (Exception e) {
				}
			}
			// {
			// // save exist
			// User user = dao.get(openid1);
			// user.setAddress("add");
			// try {
			// dao.save(user);
			// fail();
			// } catch (Exception e) {
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void messageTest() {
		try {
			long messageid = 0;
			// int messageid2 = 0;
			String openId = Util.uuid();

			Session session = HibernateUtil.openSession();
			UserDao dao = new UserDao(session);
			{
				// 写入user
				User user = new User();
				user.setOpenId(openId);
				dao.save(user);
			}
			{
				// 写入message 不用在这里设置message的openid
				User user = dao.get(openId);
				Message message = Common.createMessage();
				dao.addMessage(user, message);
				messageid = message.getId();
			}
			{
				// 获取message
				User user = dao.get(openId);
				Message message = dao.getMessage(user, 0);
				assertNotNull(message);
				assertEquals(message.getId(), messageid);
			}
			{
				// 加入第二个message
				Message message = Common.createMessage();
				User user = dao.get(openId);
				dao.addMessage(user, message);
			}
			{
				// 查询出两个message
				User user = dao.get(openId);
				assertEquals(dao.getMessageSize(user), 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
