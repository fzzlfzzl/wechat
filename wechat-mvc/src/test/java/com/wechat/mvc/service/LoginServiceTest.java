package com.wechat.mvc.service;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.web.interceptor.context.UserContext;
import com.web.interceptor.context.UserContextPool;
import com.web.service.AdminService;
import com.web.service.SuperAdminService;
import com.wechat.common.util.Util;
import com.wechat.dao.db.HibernateUtil;
import com.wechat.dao.entity.Admin;
import com.wechat.mvc.mock.MockSession;

public class LoginServiceTest {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	@Before
	public void setup() {
		request = EasyMock.createMock(HttpServletRequest.class);
		response = EasyMock.createMock(HttpServletResponse.class);
		session = new MockSession();
		EasyMock.expect(request.getSession()).andReturn(session).anyTimes();
		EasyMock.replay(request);
		Session session = HibernateUtil.openSession();
		UserContextPool.put(new UserContext(request, response, session));
	}

	@Test
	public void loginServiceTest() {
		try {
			SuperAdminService saService = new SuperAdminService();
			AdminService service = new AdminService();
			String name = Util.uuid();
			String pwd = "" + System.currentTimeMillis();
			// 没有这个人,不能登录
			{
				Admin admin = service.login(name, pwd);
				assertNull(admin);
			}
			// 加入一个admin
			{
				Admin ret = saService.addAdmin(name, pwd);
				assertNotNull(ret);
			}
			// 再加入就失败
			{
				Admin ret = saService.addAdmin(name, pwd);
				assertNull(ret);
			}
			// 用户名对，密码不对
			{
				Admin admin = service.login(name, "asf");
				assertNull(admin);
			}
			// 有这个人，可以登录
			{
				Admin admin = service.login(name, pwd);
				assertNotNull(admin);
			}
			// 登录状态正常
			{
				Admin admin = service.getLogin();
				assertNotNull(admin);
				assertEquals(admin.getName(), name);
			}
			// 删除了，不能登录
			{
				Admin admin = service.getLogin();
				saService.deleteAdmin(admin);
				admin = service.login(name, pwd);
				assertNull(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
