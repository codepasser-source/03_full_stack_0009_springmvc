package com.matt.damon.springmvc.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.matt.damon.springmvc.bean.UserBean;
import com.matt.damon.springmvc.service.UserService;

@Controller
@RequestMapping(value = "server")
public class UserController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/list.do", method = RequestMethod.GET)
	public ModelAndView list() throws Exception {
		logger.debug("list.do");

		List<UserBean> users = userService.list("00001");
		System.out.println(users);
		return new ModelAndView("/server/view");
	}

	@RequestMapping(value = "/user/modify.do", method = RequestMethod.GET)
	public ModelAndView modify() throws Exception {
		logger.debug("modify.do");

		userService.modify("00001");

		return new ModelAndView("/server/view");
	}
}
