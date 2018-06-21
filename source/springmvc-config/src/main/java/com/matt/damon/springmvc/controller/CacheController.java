package com.matt.damon.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.matt.damon.springmvc.bean.UserBean;
import com.matt.damon.springmvc.service.UserService;

@Controller
@RequestMapping(value = "/cache")
public class CacheController {

	private final Logger logger = LoggerFactory
			.getLogger(CacheController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() throws Exception {
		logger.info("list.do");
		List<UserBean> userList = userService.list("00001");

		ModelAndView mv = new ModelAndView("/view/cache.html");

		mv.addObject("users", userList);
		return mv;
	}

	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public ModelAndView modify() throws Exception {
		logger.info("modify.do");
		userService.modify("00001");
		return new ModelAndView("/view/result.hbs");
	}

	@RequestMapping(value = "/listPost.json", method = RequestMethod.POST)
	@ResponseBody
	public List<UserBean> json(@RequestBody UserBean user) {
		return userService.list("00001");
	}

	@RequestMapping(value = "/listGet.json", method = RequestMethod.GET)
	@ResponseBody
	public List<UserBean> jsonGet(@RequestParam int id) {
		List<UserBean> users = userService.list("00001");
		return users;
	}
}
