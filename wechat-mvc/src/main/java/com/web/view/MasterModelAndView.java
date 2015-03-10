package com.web.view;

import org.springframework.web.servlet.ModelAndView;

public class MasterModelAndView extends ModelAndView {

	protected MasterModelAndView() {
	}

	public MasterModelAndView(String view) {
		setViewName("_MASTER");
		addObject("_VIEW", view);
	}
}
