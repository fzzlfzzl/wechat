package com.wechat.message.handler.impl;

import com.web.interceptor.context.UserContext;
import com.wechat.common.Const.EventKey;
import com.wechat.common.Const.MenuName;
import com.wechat.dao.entity.Message;
import com.wechat.message.handler.IClickEventMessageHandler;
import com.wechat.message.handler.IMenuMessageHandler;
import com.wechat.message.handler.StateHandler;
import com.wechat.message.reply.IMessageReply;
import com.wechat.message.reply.impl.UserRedirectMessageReply;
import com.wechat.mvc.context.ContextUtil;

public class OrderMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = EventKey.ORDER;
	public static final String NAME = MenuName.ORDER;

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		String appPath = ContextUtil.getHost(UserContext.current().getRequest());
		UserRedirectMessageReply reply = new UserRedirectMessageReply(message);
		reply.setDescription("description");
		reply.setTitle("title");
		reply.setPicUrl(appPath + "img/banner.jpg");
		reply.setUrl(appPath + "order/index/" + message.getOpenId());
		return reply;
	}

	@Override
	public String getEventKey() {
		return EVENT_KEY;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
