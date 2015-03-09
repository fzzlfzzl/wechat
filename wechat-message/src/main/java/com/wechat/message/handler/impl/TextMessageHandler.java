package com.wechat.message.handler.impl;

import com.web.dao.entity.Message;
import com.wechat.message.handler.IMessageHandler;
import com.wechat.message.handler.StateHandler;
import com.wechat.message.reply.IMessageReply;
import com.wechat.message.reply.impl.TextMessageReply;

public class TextMessageHandler implements IMessageHandler {

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(message.getContent());
		return reply;
	}

}
