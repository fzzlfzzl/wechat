package com.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.site.util.HttpClient;
import com.site.util.XmlObject;

public class NormalMessageTest {

	@Test
	public void test() {
		if (!Common.testConnect("127.0.0.1", 8080)) {
			return;
		}
		String toUserName = "gh_5cb711bbf02b";
		String fromUserName = "o5bFts7d47KVX7OEIoK_DY9WJ_xY";
		String msgId = "6116423240939715753";
		String content = "你好";
		String createTime = System.currentTimeMillis() / 1000 + "";

		try {
			XmlObject req = new XmlObject("xml");
			req.get("ToUserName").setCDATA(toUserName);
			req.get("FromUserName").setCDATA(fromUserName);
			req.get("MsgId").setText(msgId);
			req.get("Content").setCDATA(content);
			req.get("CreateTime").setText(createTime);
			req.get("MsgType").setCDATA("text");
			// String message =
			// "<xml><ToUserName><![CDATA[:toUserName]]></ToUserName><MsgId>:msgId</MsgId><Content><![CDATA[:content]]></Content><MsgType><![CDATA[text]]></MsgType><CreateTime>:createTime</CreateTime><FromUserName><![CDATA[:fromUserName]]></FromUserName></xml>";
			// message = message.replace(":toUserName", toUserName);
			// message = message.replace(":fromUserName", fromUserName);
			// message = message.replace(":msgId", msgId);
			// message = message.replace(":content", content);
			// message = message.replace(":createTime", createTime);

			HttpClient client = new HttpClient("http://127.0.0.1:8080/wechat-message/message");
			System.out.println(req.toXmlString());
			String res = client.post(req.toXmlString());
			XmlObject resObj = XmlObject.toXmlObject(res);
			System.out.println(resObj.toXmlString());
			assertEquals(resObj.get("FromUserName").getText(), toUserName);
			assertEquals(resObj.get("ToUserName").getText(), fromUserName);
			assertEquals(resObj.get("CreateTime").getText(), createTime);
			assertEquals(resObj.get("MsgType").getText(), "text");
			assertEquals(resObj.get("Content").getText(), content);
			// assertEquals(resObj.get("ToUserName").getText(),
			// Common.USER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
