package com.test.web;

import java.net.Socket;

import com.site.util.XmlObject;

public class Common {
	public static final String APP_NAME = "gh_5cb711bbf02b";
	public static final String USER_NAME = "o5bFts7d47KVX7OEIoK_DY9WJ_xY";

	public static XmlObject createClickEventMessage(String key) {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA(APP_NAME);
		req.get("FromUserName").setCDATA(USER_NAME);
		req.get("CreateTime").setText("" + System.currentTimeMillis() / 1000);
		req.get("MsgType").setCDATA("event");
		req.get("Event").setCDATA("CLICK");
		req.get("EventKey").setCDATA(key);
		return req;
	}

	public static XmlObject createTextMessage(String content) {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA(APP_NAME);
		req.get("FromUserName").setCDATA(USER_NAME);
		req.get("CreateTime").setText("" + System.currentTimeMillis() / 1000);
		req.get("MsgType").setCDATA("text");
		req.get("MsgId").setText("" + System.currentTimeMillis());
		req.get("Content").setCDATA(content);
		return req;
	}

	public static boolean testConnect(String ip, int port) {
		try {
			Socket socket = new Socket(ip, port);
			socket.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
