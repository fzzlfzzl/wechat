package com.wechat.message.menu.impl;

import java.util.ArrayList;
import java.util.List;

import com.site.util.JsonObject;
import com.wechat.message.menu.IButton;

public class SubButton implements IButton {

	private String name = null;
	private List<IButton> list = new ArrayList<IButton>();

	public SubButton(String name) {
		this.name = name;
	}

	public void addChild(IButton child) {
		list.add(child);
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.get("name").set(name);
		for (IButton button : list) {
			obj.get("sub_button").add(button.toJsonObject());
		}
		return obj;
	}
}
