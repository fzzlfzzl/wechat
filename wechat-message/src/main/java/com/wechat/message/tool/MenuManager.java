package com.wechat.message.tool;

import org.apache.log4j.Logger;

import com.wechat.common.util.HttpsClient;
import com.wechat.common.util.JsonObject;
import com.wechat.message.handler.impl.OrderMessageHandler;
import com.wechat.message.menu.impl.ClickButton;
import com.wechat.message.menu.impl.MainButton;
import com.wechat.message.menu.impl.SubButton;

public class MenuManager {

	private static Logger logger = Logger.getLogger(MenuManager.class);
	private static MainButton main = null;

	public static void initMenu() {
		SubButton sub = new SubButton("二级菜单");
		main.addChild(sub);

		ClickButton click = new ClickButton(OrderMessageHandler.NAME, OrderMessageHandler.EVENT_KEY);
		sub.addChild(click);
	}

	public synchronized static String getRequest() {
		if (null == main) {
			main = new MainButton();
			initMenu();
		}
		return main.toJsonObject().toJsonString();
	}

	private static String getRegistMenuUrl(String accessToken) {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
		String url = String.format(urlFmt, accessToken);
		return url;
	}

	public static void registMenu(String req) {
		String token = AccessTokenManager.getAccessToken();
		String url = getRegistMenuUrl(token);
		logger.info("Regist Menu Url: " + url);
		HttpsClient client = new HttpsClient(url);
		logger.info("Regist Menu Req: " + req);
		String res = null;
		res = client.post(req);
		logger.info("Regist Menu Res: " + res);
		JsonObject obj = JsonObject.toJsonObject(res);
		if (obj.get("errcode").toInt() != 0) {
			logger.error(res);
			throw new RuntimeException("Regist Menu Fail");
		}
	}
}
