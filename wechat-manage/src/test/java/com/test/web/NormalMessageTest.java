package com.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.site.util.HttpClient;
import com.site.util.XmlObject;

public class NormalMessageTest {
	public static void main(String[] args) {
		try {
			String message = "<xml><ToUserName><![CDATA[gh_5cb711bbf02b]]></ToUserName><MsgId>6116423240939715753</MsgId><Content><![CDATA[hello]]></Content><MsgType><![CDATA[text]]></MsgType><CreateTime>1424090760</CreateTime><FromUserName><![CDATA[o5bFts7d47KVX7OEIoK_DY9WJ_xY]]></FromUserName></xml>";
			HttpClient client = new HttpClient("http://127.0.0.1:8080/wechat/wechat/service");
			System.out.println(message);
			String res = client.post(message);
			System.out.println(res);
			XmlObject resObj = XmlObject.toXmlObject(res);
			assertEquals(resObj.get("FromUserName").getText(), Common.APP_NAME);
			assertEquals(resObj.get("ToUserName").getText(), Common.USER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
