package com.wechat.message.service;

import java.net.Socket;

import org.apache.log4j.Logger;

import com.wechat.common.util.XmlObject;

public class Common {

	public static Logger logger = Logger.getLogger(Common.class);

	public static final String APP_NAME = "gh_5cb711bbf02b";

	public static XmlObject createClickEventMessage(String openid, String key) {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA(APP_NAME);
		req.get("FromUserName").setCDATA(openid);
		req.get("CreateTime").setText(System.currentTimeMillis() / 1000);
		req.get("MsgType").setCDATA("event");
		req.get("Event").setCDATA("CLICK");
		req.get("MsgId").setText("" + System.currentTimeMillis());
		req.get("EventKey").setCDATA(key);
		return req;
	}

	public static XmlObject createTextMessage(String openid, String content) {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA(APP_NAME);
		req.get("FromUserName").setCDATA(openid);
		req.get("CreateTime").setText(System.currentTimeMillis() / 1000);
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
