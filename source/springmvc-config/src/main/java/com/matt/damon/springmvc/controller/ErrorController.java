package com.matt.damon.springmvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matt.damon.springmvc.bean.UserBean;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

	@RequestMapping(value = "/listPost.json", method = RequestMethod.POST)
	@ResponseBody
	public List<UserBean> json(@RequestBody UserBean user) {
		int[] s = { 123, 3 };
		System.out.println(s[3]);
		return null;
	}
}
