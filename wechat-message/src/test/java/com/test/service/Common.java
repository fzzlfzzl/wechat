package com.test.service;

import com.site.util.Util;
import com.site.util.XmlObject;

public class Common {

	public static final String APP_NAME = "gh_5cb711bbf02b";

	public static XmlObject createClickEventMessage(String openid, String key) {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA(APP_NAME);
		req.get("FromUserName").setCDATA(openid);
		req.get("CreateTime").setText(System.currentTimeMillis() / 1000);
		req.get("MsgType").setCDATA("event");
		req.get("Event").setCDATA("CLICK");
		req.get("MsgId").setText(Util.random());
		req.get("EventKey").setCDATA(key);
		return req;
	}

	public static XmlObject createTextMessage(String openid, String content) {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA(APP_NAME);
		req.get("FromUserName").setCDATA(openid);
		req.get("CreateTime").setText(System.currentTimeMillis() / 1000);
		req.get("MsgType").setCDATA("text");
		req.get("MsgId").setText(Util.random());
		req.get("Content").setCDATA(content);
		return req;
	}
}
