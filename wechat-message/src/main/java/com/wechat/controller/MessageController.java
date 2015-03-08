package com.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wechat.message.WechatService;

@Controller
@RequestMapping("/message")
public class MessageController {

	@RequestMapping(value = { "", "/", "/*" }, method = RequestMethod.GET)
	public void get(HttpServletRequest request, HttpServletResponse response) {
		WechatService.doGet(request, response);
	}

	@RequestMapping(value = { "", "/", "/*" }, method = RequestMethod.POST)
	public void post(HttpServletRequest request, HttpServletResponse response) {
		WechatService.doPost(request, response);
	}
}
