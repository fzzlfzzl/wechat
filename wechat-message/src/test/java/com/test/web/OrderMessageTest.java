package com.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.site.util.HttpClient;
import com.site.util.Util;
import com.site.util.XmlObject;
import com.test.service.Common;
import com.web.dao.db.HibernateUtil;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;

public class OrderMessageTest {

	private User user = null;

	@Before
	public void setup() {
		Session session = HibernateUtil.openSession();
		UserDao dao = new UserDao(session);
		List<User> list = dao.list();
		if (list.size() == 0) {
			user = new User();
			user.setOpenId(Util.uuid());
			dao.save(user);
		} else {
			user = list.get(0);
		}
	}

	@Test
	public void test() {
		if (!Common.testConnect("127.0.0.1", 8080)) {
			return;
		}
		String appPath = "http://127.0.0.1:8080/wechat-message/";

		try {
			HttpClient client = new HttpClient("http://127.0.0.1:8080/wechat-message/message");
			String picUrl = appPath + "img/banner.jpg";
			String url = appPath + "order/" + user.getOpenId();
			XmlObject req = Common.createClickEventMessage(user.getOpenId(), "ORDER");
			String resStr = client.post(req.toXmlString());
			XmlObject res = XmlObject.toXmlObject(resStr);
			System.out.println(res.toXmlString());
			assertEquals(res.get("MsgType").getText(), "news");
			assertEquals(res.get("ArticleCount").getText(), "1");
			res = res.get("Articles").get("item");
			assertEquals(res.get("Title").getText(), "title");
			assertEquals(res.get("Description").getText(), "description");
			assertEquals(res.get("PicUrl").getText(), picUrl);
			assertEquals(res.get("Url").getText(), url);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testReal() {
		String appPath = "http://95wechat.duapp.com:80/";
		try {
			HttpClient client = new HttpClient("http://95wechat.duapp.com/message");
			String picUrl = appPath + "img/banner.jpg";
			String url = appPath + "order/index/" + user.getOpenId();
			XmlObject req = Common.createClickEventMessage(user.getOpenId(), "ORDER");
			String resStr = client.post(req.toXmlString());
			XmlObject res = XmlObject.toXmlObject(resStr);
			System.out.println(res.toXmlString());
			assertEquals(res.get("MsgType").getText(), "news");
			assertEquals(res.get("ArticleCount").getText(), "1");
			res = res.get("Articles").get("item");
			assertEquals(res.get("Title").getText(), "title");
			assertEquals(res.get("Description").getText(), "description");
			assertEquals(res.get("PicUrl").getText(), picUrl);
			assertEquals(res.get("Url").getText(), url);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
