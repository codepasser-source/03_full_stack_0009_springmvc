package com.matt.damon.springmvc.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.matt.damon.springmvc.controller.BaseController;
import com.matt.damon.springmvc.service.UserService;

@Controller
@RequestMapping(value = "admin")
public class UserController extends BaseController {

	private final Log logger = LogFactory.getLog(this.getClass());

	private String docContent;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/save.do", method = RequestMethod.GET)
	public ModelAndView save() throws Exception {
		logger.debug("UserController----->save");
		userService.saveUser();

		docContent = "<h1>text</h1>";

		return new ModelAndView("/server/view", "docContent", docContent);
	}

	public String getDoc() {
		return docContent;
	}

	public void setDoc(String docContent) {
		this.docContent = docContent;
	}

}
