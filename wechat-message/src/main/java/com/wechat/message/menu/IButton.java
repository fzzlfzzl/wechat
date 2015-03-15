package com.wechat.message.menu;

import com.wechat.common.util.JsonObject;

public interface IButton {

	public static String TYPE_CLICK = "click";
	public static String TYPE_VIEW = "view";

	public JsonObject toJsonObject();
}
