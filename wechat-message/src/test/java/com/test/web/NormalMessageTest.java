package com.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.site.util.HttpClient;
import com.site.util.XmlObject;

public class NormalMessageTest {

	public static void main(String[] args) {
		String toUserName = "gh_5cb711bbf02b";
		String fromUserName = "o5bFts7d47KVX7OEIoK_DY9WJ_xY";
		String msgId = "6116423240939715753";
		String content = "你好";
		String createTime = System.currentTimeMillis() / 1000 + "";
		try {
			String message = "<xml><ToUserName><![CDATA[:toUserName]]></ToUserName><MsgId>:msgId</MsgId><Content><![CDATA[:content]]></Content><MsgType><![CDATA[text]]></MsgType><CreateTime>:createTime</CreateTime><FromUserName><![CDATA[:fromUserName]]></FromUserName></xml>";
			message = message.replace(":toUserName", toUserName);
			message = message.replace(":fromUserName", fromUserName);
			message = message.replace(":msgId", msgId);
			message = message.replace(":content", content);
			message = message.replace(":createTime", createTime);

			HttpClient client = new HttpClient("http://127.0.0.1:8080/wechat-message/message");
			System.out.println(message);
			String res = client.post(message);
			System.out.println(res);
			XmlObject resObj = XmlObject.toXmlObject(res);
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
