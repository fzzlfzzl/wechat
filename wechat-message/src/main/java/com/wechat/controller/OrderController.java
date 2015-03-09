package com.wechat.controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.WebController;
import com.web.dao.entity.Orders;
import com.web.interceptor.context.UserContext;

@Controller
@RequestMapping("/order")
public class OrderController extends WebController {

	// private static Logger logger = Logger.getLogger(AccountController.class);

	@RequestMapping(value = "/{openid}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable String openid) throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		ret.addObject("openid", openid);
		return ret;
	}

	@RequestMapping(value = "/deal", method = RequestMethod.POST)
	public ModelAndView deal(String openid, String dishes, String name, String telephone, String address)
			throws Exception {
		ModelAndView ret = createNormalModelAndView("deal");
		Session session = UserContext.current().getSession();
		session.beginTransaction();
		Orders order = new Orders();
		order.setOpenid(openid);
		order.setDishes(dishes);
		order.setTelephone(telephone);
		order.setAddress(address);
		session.save(order);
		session.getTransaction().commit();
		return ret;
	}
}
