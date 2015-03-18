package com.wechat.controller;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.WebController;
import com.web.interceptor.context.UserContext;
import com.web.view.View;
import com.web.view.site.admin.OrderListView;
import com.wechat.dao.entity.Orders;

@Controller
@RequestMapping("/site")
public class SiteController extends WebController {

	// private static Logger logger = Logger.getLogger(SiteController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		Session session = UserContext.current().getSession();
		List<Orders> list = session.createQuery("from Orders").list();
		View view = new OrderListView(list);
		ret.addObject("orderList", view);
		return ret;
	}

	@RequestMapping(value = "/dishes/add", method = RequestMethod.GET)
	public ModelAndView dishesAddGet() throws Exception {
		ModelAndView ret = createNormalModelAndView("dishes/add");
		ret.addObject("url", url("dishes/add"));
		return ret;
	}

	@RequestMapping(value = "/dishes/add", method = RequestMethod.POST)
	public ModelAndView dishesAddPost(String name, String desc, String price, String[] tasteName, String[] tastePrice)
			throws Exception {
		ModelAndView ret = createNormalModelAndView("dishes/add");
		return ret;
	}
}
