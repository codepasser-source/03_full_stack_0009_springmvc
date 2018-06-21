package com.matt.damon.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.matt.damon.springmvc.bean.UserBean;

@Controller
@RequestMapping(value = "/file")
public class FileController {

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	@ResponseBody
	public UserBean save(HttpServletRequest request,
			@ModelAttribute("masterplate") UserBean user,
			@RequestParam MultipartFile effectFile) {
		System.out.println(user);
		return user;
	}
}
