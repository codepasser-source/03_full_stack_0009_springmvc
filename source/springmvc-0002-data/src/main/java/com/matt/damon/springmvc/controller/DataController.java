package com.matt.damon.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matt.damon.springmvc.bean.UserBean;

@Controller
@RequestMapping(value = "server")
public class DataController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "json.do", method = RequestMethod.POST)
	@ResponseBody
	public List<UserBean> json(@RequestBody UserBean user) {
		UserBean user1 = new UserBean();
		user1.setName("tom");
		user1.setPassword("cat");
		UserBean user2 = new UserBean();
		user2.setName("tom");
		user2.setPassword("cat");
		List<UserBean> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		logger.debug("json.do");
		return users;
	}

	@RequestMapping(value = "jsonGet.do", method = RequestMethod.GET)
	@ResponseBody
	public List<UserBean> jsonGet() {
		UserBean user1 = new UserBean();
		user1.setName("tom");
		user1.setPassword("cat");
		UserBean user2 = new UserBean();
		user2.setName("tom");
		user2.setPassword("cat");
		List<UserBean> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		logger.debug("jsonGet.do");
		return users;
	}
}
