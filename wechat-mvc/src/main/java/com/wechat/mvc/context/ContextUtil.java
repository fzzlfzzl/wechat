package com.wechat.mvc.context;

import javax.servlet.http.HttpServletRequest;

public class ContextUtil {

	public static String getHost(HttpServletRequest request) {
		String fmt = "http://%s:%d%s/";
		String ret = String.format(fmt, request.getServerName(), request.getServerPort(), request.getContextPath());
		return ret;
	}
}
