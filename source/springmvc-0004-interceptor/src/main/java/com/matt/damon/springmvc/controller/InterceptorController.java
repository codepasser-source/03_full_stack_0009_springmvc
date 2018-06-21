package com.matt.damon.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "server")
public class InterceptorController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "interceptor.do", method = RequestMethod.GET)
	public ModelAndView systemError() throws Exception {
		logger.debug("interceptor.do");

		return new ModelAndView("/server/view");
	}

}
