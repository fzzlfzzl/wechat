package com.wechat.controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.WebController;
import com.web.interceptor.context.UserContext;
import com.wechat.dao.entity.Orders;

@Controller
@RequestMapping("/order")
public class OrderController extends WebController {

	// private static Logger logger = Logger.getLogger(AccountController.class);

	@RequestMapping(value = "/index/{openid}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable String openid) throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		ret.addObject("openid", openid);
		ret.addObject("dealUrl", url("deal"));
		return ret;
	}

	@RequestMapping(value = "/deal", method = RequestMethod.POST)
	public ModelAndView deal(String openid, String dishes, String name, String telephone, String address)
			throws Exception {
		ModelAndView ret = createAjaxModelAndView("deal");
		Session session = UserContext.current().getSession();
		session.beginTransaction();
		Orders order = new Orders();
		order.setOpenid(openid);
		order.setName(name);
		order.setDishes(dishes);
		order.setTelephone(telephone);
		order.setAddress(address);
		session.save(order);
		session.getTransaction().commit();
		return ret;
	}
}
