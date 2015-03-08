package com.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.site.Config;
import com.site.util.HttpClient;
import com.site.util.XmlObject;

public class OrderMessageTest {

	@Test
	public void test() {
		if (!Common.testConnect("127.0.0.1", 8080)) {
			return;
		}
		try {
			HttpClient client = new HttpClient("http://127.0.0.1:8080/wechat-message/message");
			String picUrl = Config.getHost() + "img/banner.jpg";
			String url = Config.getHost() + "order/index?openid=" + Common.USER_NAME;
			XmlObject req = Common.createClickEventMessage("ORDER");
			String resStr = client.post(req.toXmlString());
			System.out.println(resStr);
			XmlObject res = XmlObject.toXmlObject(resStr);
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
