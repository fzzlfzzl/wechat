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
		return new AdminDao(UserContext.current().getSession()).list();
	}

	public boolean addAdmin(String name, String pwd) {		Admin admin = new Admin();
		admin.setName(name);
		admin.setPassword(Util.sha1(pwd));
		AdminDao dao = new AdminDao(UserContext.current().getSession());
		try(
			dao.save(admin);
			return admin;
		}catch(RuntimeException e){
			return null;
		}
		
}

	public void deleteAdmin(long id) {
		Admin admin = new Admin();
		admin.setId(id);
		deleteAdmin(admin);
	}

	public void deleteAdmin(Admin admin) { 
		new AdminDao(UserContext.current().getSession()).delete(admin);
	}

}
