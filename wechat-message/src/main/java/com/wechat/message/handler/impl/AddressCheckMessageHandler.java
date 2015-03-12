package com.wechat.message.handler.impl;

import com.service.wechat.Const.EventKey;
import com.service.wechat.Const.MenuName;
import com.service.wechat.Const.Msg;
import com.service.wechat.Const.MsgReply;
import com.service.wechat.Const.Type;
import com.wechat.dao.entity.Message;
import com.wechat.message.handler.IClickEventMessageHandler;
import com.wechat.message.handler.IMenuMessageHandler;
import com.wechat.message.handler.StateHandler;
import com.wechat.message.reply.IMessageReply;
import com.wechat.message.reply.impl.TextMessageReply;

/**
 * 请用户确认
 * 
 * @author yml
 * 
 */
public class AddressCheckMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = EventKey.ADDRESS;
	public static final String NAME = MenuName.ADDRESS;

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		if (!message.getMsgType().equals(Type.TEXT)) {
			return null;
		}
		if (!message.getContent().equals(Msg.YES)) {
			return null;
		}
		state.setNextHandler(new AddressUpdateMessageHandler());
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(MsgReply.ADDR);
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
