package com.wechat.message.handler.impl;

import com.service.wechat.Const.EventKey;
import com.service.wechat.Const.MenuName;
import com.service.wechat.Const.MsgReply;
import com.web.interceptor.context.UserContext;
import com.wechat.dao.entity.Message;
import com.wechat.dao.entity.User;
import com.wechat.dao.impl.UserDao;
import com.wechat.message.handler.IClickEventMessageHandler;
import com.wechat.message.handler.IMenuMessageHandler;
import com.wechat.message.handler.StateHandler;
import com.wechat.message.reply.IMessageReply;
import com.wechat.message.reply.impl.TextMessageReply;

public class AddressMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = EventKey.ADDRESS;
	public static final String NAME = MenuName.ADDRESS;

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		state.setNextHandler(new AddressUpdateMessageHandler());
		TextMessageReply reply = new TextMessageReply(message);
		User user = new UserDao(UserContext.current().getSession()).get(message.getOpenId());
		if (null != user.getAddress()) {
			// 已有地址，需要确认
			state.setNextHandler(new AddressCheckMessageHandler());
			String content = String.format(MsgReply.ADDR_CHK, user.getAddress());
			reply.setContent(content);
		} else {
			// 没有地址，直接填写
			reply.setContent(MsgReply.ADDR);
			state.setNextHandler(new AddressUpdateMessageHandler());
		}
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
