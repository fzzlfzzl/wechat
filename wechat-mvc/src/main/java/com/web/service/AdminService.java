package com.web.service;

import com.web.interceptor.context.UserContext;
import com.wechat.common.Const.Auth;
import com.wechat.common.util.Util;
import com.wechat.dao.entity.Admin;
import com.wechat.dao.impl.AdminDao;

public class AdminService {

	public Admin getLogin() {
		Admin admin = (Admin) UserContext.current().getRequest().getSession().getAttribute(Auth.LOGIN);
		return admin;
	}

	public Admin login(String name, String pwd) {
		Admin admin = AdminDao.load(name);
		if (admin == null) {
			return null;
		}
		if (Util.sha1(pwd).equals(admin.getPassword())) {
			UserContext.current().getRequest().getSession().setAttribute(Auth.LOGIN, admin);
			return admin;
		} else {
			return null;
		}

	}

	public void logout() {
		UserContext.current().getRequest().getSession().removeAttribute(Auth.LOGIN);
	}

}
