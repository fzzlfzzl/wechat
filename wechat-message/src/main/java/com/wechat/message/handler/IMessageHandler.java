package com.wechat.message.handler;

import com.web.dao.entity.Message;
import com.wechat.message.reply.IMessageReply;

public interface IMessageHandler {

	public IMessageReply handleMessage(Message message, StateHandler state);

}