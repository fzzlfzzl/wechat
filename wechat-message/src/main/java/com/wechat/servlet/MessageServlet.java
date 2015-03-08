package com.wechat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.message.WechatService;

public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public MessageServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WechatService.doGet(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		WechatService.doPost(request, response);
	}

	public void init() throws ServletException {
	}

}
