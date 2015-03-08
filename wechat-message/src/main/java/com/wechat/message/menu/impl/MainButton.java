package com.wechat.message.menu.impl;

import java.util.ArrayList;
import java.util.List;

import com.site.util.JsonObject;
import com.wechat.message.menu.IButton;

public class MainButton implements IButton {

	private List<IButton> list = new ArrayList<IButton>();

	public void addChild(IButton child) {
		list.add(child);
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		for (IButton button : list) {
			obj.get("button").add(button.toJsonObject());
		}
		return obj;
	}

}
