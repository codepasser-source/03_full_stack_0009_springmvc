package com.mattdamon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() throws Exception {
		ModelAndView mv = new ModelAndView("/view/list.html");
		return mv;
	}

}
