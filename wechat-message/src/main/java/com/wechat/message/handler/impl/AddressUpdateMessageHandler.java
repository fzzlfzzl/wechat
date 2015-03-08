package com.wechat.message.handler.impl;

import org.apache.log4j.Logger;

import com.service.wechat.Const.MsgReply;
import com.service.wechat.Const.Type;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;
import com.web.interceptor.context.UserContext;
import com.wechat.message.handler.IMessageHandler;
import com.wechat.message.handler.StateHandler;
import com.wechat.message.reply.IMessageReply;
import com.wechat.message.reply.impl.TextMessageReply;

public class AddressUpdateMessageHandler implements IMessageHandler {

	static Logger logger = Logger.getLogger(AddressMessageHandler.class);

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		if (!message.getMsgType().equals(Type.TEXT)) {
			logger.info("Not Suitable Message");
			return null;
		}
		UserDao dao = new UserDao(UserContext.current().getSession());
		User user = dao.get(message.getOpenId());
		user.setAddress(message.getContent());
		dao.update(user);
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(MsgReply.ADDR_UPDT_SUCC);
		return reply;
	}

}
