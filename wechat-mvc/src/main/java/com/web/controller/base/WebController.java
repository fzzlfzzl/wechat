package com.web.controller.base;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.interceptor.context.UserContext;
import com.web.view.MasterModelAndView;

public class WebController {

	protected String controllerName = thisController();

	protected String thisController() {
		RequestMapping rm = this.getClass().getAnnotation(RequestMapping.class);
		if (rm == null) {
			throw new RuntimeException("No RequestMapping");
		}
		String[] value = rm.value();
		if (value.length != 1) {
			throw new RuntimeException("Invalid Mapping");
		}
		String controller = value[0].substring(1);
		return controller;
	}

	protected String url(String view) {
		UserContext context = UserContext.current();
		String url = null;
		if (viewContainController(view)) {
			url = String.format("%s/%s", context.getRequest().getContextPath(), view);
		} else {
			url = String.format("%s/%s/%s", context.getRequest().getContextPath(), controllerName, view);
		}
		return url;
	}

	protected String concatControllerName(String view) {
		String ret = String.format("%s/%s", controllerName, view);
		return ret;
	}

	protected ModelAndView createNormalModelAndView(String view) {
		if (viewContainController(view)) {
			return new MasterModelAndView(view);
		} else {
			return new MasterModelAndView(concatControllerName(view));
		}
	}

	protected ModelAndView createAjaxModelAndView(String view) {
		if (viewContainController(view)) {
			return new ModelAndView(view);
		} else {
			return new ModelAndView(concatControllerName(view));
		}
	}

	protected ModelAndView createRedirectModelAndView(String view) {
		UserContext context = UserContext.current();
		String url = url(view);
		try {
			context.getResponse().sendRedirect(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	protected ModelAndView createForwardModelAndView(String view) {
		String url = null;
		if (viewContainController(view)) {
			url = String.format("forward:/%s", view);
		} else {
			url = String.format("forward:/%s/%s", thisController(), view);
		}
		return new ModelAndView(url);
	}

	private boolean viewContainController(String view) {
		return view.indexOf(controllerName) >= 0;
	}
}
