package com.wechat.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.site.util.ByteBuffer;
import com.site.util.ExceptionLogger;
import com.site.util.Util;
import com.site.util.XmlObject;
import com.web.dao.db.HibernateUtil;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;
import com.web.interceptor.context.UserContext;
import com.web.interceptor.context.UserContextPool;
import com.wechat.message.factory.MessageFactory;
import com.wechat.message.handler.StateHandler;
import com.wechat.message.reply.IMessageReply;

public class WechatService {
	private static String TOKEN = "jiuwubaodu";

	private static Logger logger = Logger.getLogger(WechatService.class);

	public static void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("Get Request:" + request.getQueryString());
			String signature = request.getParameter("signature");
			String echostr = request.getParameter("echostr");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");

			if (invalidParam(signature, echostr, timestamp, nonce)) {
				logger.warn("Invalid Param");
				response.getWriter().print("Invalid Param");
				return;
			}
			String[] str = { TOKEN, timestamp, nonce };
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
			String digest = Util.sha1(bigStr).toLowerCase();
			if (digest.equals(signature)) {
				logger.info("Get Response:" + echostr);
				response.getWriter().print(echostr);
			} else {
				logger.error("Invalid signature");
			}
		} catch (Exception e) {
			logger.error(new ExceptionLogger(e));
			throw new RuntimeException(e);
		}
	}

	private static int[] orig = new int[] { 60, 120, 109, 108, 62, 60, 84, 111, 85, 115, 101, 114, 78, 97, 109, 101,
			62, 60, 33, 91, 67, 68, 65, 84, 65, 91, 103, 104, 95, 53, 99, 98, 55, 49, 49, 98, 98, 102, 48, 50, 98, 93,
			93, 62, 60, 47, 84, 111, 85, 115, 101, 114, 78, 97, 109, 101, 62, 60, 77, 115, 103, 73, 100, 62, 54, 49,
			49, 54, 52, 50, 51, 50, 52, 48, 57, 51, 57, 55, 49, 53, 55, 53, 51, 60, 47, 77, 115, 103, 73, 100, 62, 60,
			67, 111, 110, 116, 101, 110, 116, 62, 60, 33, 91, 67, 68, 65, 84, 65, 91, -28, -67, -96, -27, -91, -67, 93,
			93, 62, 60, 47, 67, 111, 110, 116, 101, 110, 116, 62, 60, 77, 115, 103, 84, 121, 112, 101, 62, 60, 33, 91,
			67, 68, 65, 84, 65, 91, 116, 101, 120, 116, 93, 93, 62, 60, 47, 77, 115, 103, 84, 121, 112, 101, 62, 60,
			67, 114, 101, 97, 116, 101, 84, 105, 109, 101, 62, 49, 52, 50, 52, 48, 57, 48, 55, 54, 48, 60, 47, 67, 114,
			101, 97, 116, 101, 84, 105, 109, 101, 62, 60, 70, 114, 111, 109, 85, 115, 101, 114, 78, 97, 109, 101, 62,
			60, 33, 91, 67, 68, 65, 84, 65, 91, 111, 53, 98, 70, 116, 115, 55, 100, 52, 55, 75, 86, 88, 55, 79, 69, 73,
			111, 75, 95, 68, 89, 57, 87, 74, 95, 120, 89, 93, 93, 62, 60, 47, 70, 114, 111, 109, 85, 115, 101, 114, 78,
			97, 109, 101, 62, 60, 47, 120, 109, 108, 62 };

	public static void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			prehandle(request, response);

			XmlObject reqObject = XmlObject.toXmlObject(request.getInputStream());
			String req = reqObject.toXmlString();
			logger.info("Request:" + req);

			XmlObject resObject = service(reqObject);
			String res = resObject.toXmlString();
			logger.info("Response:" + res);
			
			response.getWriter().print(res);
		} catch (Exception e) {
			logger.error(new ExceptionLogger(e));
			throw new RuntimeException(e);
		}
	}

	private static ByteBuffer readBytes(HttpServletRequest request) {
		try {
			ByteBuffer buffer = new ByteBuffer();
			byte[] tmp = new byte[1024];
			int ret = request.getInputStream().read(tmp);
			while (ret > 0) {
				buffer.push(tmp, 0, ret);
				ret = request.getInputStream().read(tmp);
			}
			return buffer;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void logBytes(byte[] bs, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			byte b = bs[i];
			String s = new String(new byte[] { b });
			String item = String.format("%d(%s)", (int) b, s);
			sb.append(item);
		}
		logger.info(sb.toString());
		sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			byte b = bs[i];
			String item = String.format("%d,", (int) b);
			sb.append(item);
		}
		logger.info(sb.toString());
	}

	private static void prehandle(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserContext context = new UserContext(request, response, HibernateUtil.openSession());
		UserContextPool.put(context);
	}

	public static XmlObject service(XmlObject reqObject) {
		Message message = MessageFactory.createMessage(reqObject);
		logMessage(message);

		StateHandler handler = StateHandler.byUser(message.getOpenId());
		IMessageReply reply = handler.handleMessage(message);
		XmlObject resObject = reply.getResponse();
		return resObject;

	}

	private static void logMessage(Message message) {
		UserDao dao = new UserDao(UserContext.current().getSession());
		User user = dao.get(message.getOpenId());
		if (user == null) {
			user = new User();
			user.setOpenId(message.getOpenId());
			dao.save(user);
		}
		dao.addMessage(user, message);
	}

	private static boolean invalidParam(String signature, String echostr, String timestamp, String nonce) {
		return null == signature || null == echostr || null == timestamp || null == nonce;
	}
}
