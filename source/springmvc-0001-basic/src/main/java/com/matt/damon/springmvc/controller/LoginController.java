package com.matt.damon.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.matt.damon.springmvc.bean.UserBean;

@Controller
@RequestMapping(value = "server")
public class LoginController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, UserBean user) {
		String username = user.getName();

		logger.debug("login");

		ModelAndView mv = new ModelAndView("/server/login-success", "msg",
				"LOGIN SUCCESS, " + username);
		return mv;
	}
}
