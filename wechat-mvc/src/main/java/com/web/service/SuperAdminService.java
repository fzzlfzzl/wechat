package com.web.service;

import java.util.List;

import com.web.interceptor.context.UserContext;
import com.wechat.common.Const.Auth;
import com.wechat.common.util.Util;
import com.wechat.dao.entity.Admin;
import com.wechat.dao.impl.AdminDao;

public class SuperAdminService {

	public boolean isLogin() {
		if (Auth.SA_LOGIN.equals(UserContext.current().getRequest().getSession().getAttribute(Auth.SA_LOGIN))) {
			return true;
		}
		return false;
	}

	public boolean login(String user, String pwd) {
		if (user.equals("sa") && pwd.equals("111111")) {
			UserContext.current().getRequest().getSession().setAttribute(Auth.SA_LOGIN, Auth.SA_LOGIN);
			return true;
		}
		return false;
	}

	static void logout() {
		UserContext.current().getRequest().getSession().removeAttribute(Auth.SA_LOGIN);
	}

	public List<Admin> getAdminList() {
		return AdminDao.list();
	}

	public boolean addAdmin(String name, String pwd) {
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPassword(Util.sha1(pwd));
		try {
			AdminDao.save(admin);
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}

	public void deleteAdmin(long id) {
		AdminDao.delete(id);
	}

	public void deleteAdmin(Admin admin) {
		deleteAdmin(admin.getId());
	}

}
