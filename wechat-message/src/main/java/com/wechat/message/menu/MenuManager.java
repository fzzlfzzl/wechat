package com.wechat.message.menu;

import com.wechat.message.handler.impl.OrderMessageHandler;
import com.wechat.message.menu.impl.ClickButton;
import com.wechat.message.menu.impl.MainButton;
import com.wechat.message.menu.impl.SubButton;

public class MenuManager {

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

}
