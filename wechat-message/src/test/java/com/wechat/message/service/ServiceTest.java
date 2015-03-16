package com.wechat.message.service;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.web.interceptor.context.UserContext;
import com.web.interceptor.context.UserContextPool;
import com.wechat.common.Const.MsgReply;
import com.wechat.common.util.Util;
import com.wechat.common.util.XmlObject;
import com.wechat.dao.db.HibernateUtil;
import com.wechat.message.WechatService;
import com.wechat.message.handler.impl.AddressMessageHandler;

public class ServiceTest {

	static Logger logger = Logger.getLogger("Test");

	// @Test
	// public void tmp() {
	// List<Class<?>> ret = ClassUtil2.getClasses("com.wechat.dao.entity");
	// assert (ret != null);
	// for (int i = 0; i < ret.size(); i++) {
	// System.out.println(ret.get(i).getName());
	// }
	// }

	@Before
	public void setup() {
		UserContext context = new UserContext(null, null, HibernateUtil.openSession());
		UserContextPool.put(context);
	}

	@Test
	public void addressUpdateTest() {

		String addr1 = "地址1";
		String addr2 = "地址2";
		String addr3 = "地址3";
		String openid = Util.uuid();
		{
			// 第一次请求
			XmlObject req = Common.createTextMessage(openid, "hello");
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), "hello");
		}
		{
			// 点击地址按钮
			XmlObject req = Common.createClickEventMessage(openid, AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), MsgReply.ADDR);
		}
		{
			// 输入地址
			XmlObject req = Common.createTextMessage(openid, addr1);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), MsgReply.ADDR_UPDT_SUCC);
		}
		{
			// 再次点击地址按钮，判断地址
			XmlObject req = Common.createClickEventMessage(openid, AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			String check = String.format(MsgReply.ADDR_CHK, addr1);
			assertEquals(res.get("Content").getText(), check);
		}
		{
			// 输入是
			XmlObject req = Common.createTextMessage(openid, "是");
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), MsgReply.ADDR);
		}
		{
			// 输入地址2
			XmlObject req = Common.createTextMessage(openid, addr2);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), MsgReply.ADDR_UPDT_SUCC);
		}
		{
			// 再次点击地址按钮,判断地址2
			XmlObject req = Common.createClickEventMessage(openid, AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			String check = String.format(MsgReply.ADDR_CHK, addr2);
			assertEquals(res.get("Content").getText(), check);
		}
		{
			// 再次点击地址按钮,依然是2
			XmlObject req = Common.createClickEventMessage(openid, AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			String check = String.format(MsgReply.ADDR_CHK, addr2);
			assertEquals(res.get("Content").getText(), check);
		}
		{
			// 不输入是，输入其他烂七八糟
			XmlObject req = Common.createTextMessage(openid, addr3);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), Common.USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), addr3);
		}
		{
			// 再次点击地址按钮,依然是2,不更新
			XmlObject req = Common.createClickEventMessage(openid, AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), Common.APP_NAME);
			// assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			String check = String.format(MsgReply.ADDR_CHK, addr2);
			assertEquals(res.get("Content").getText(), check);
		}
	}

}
