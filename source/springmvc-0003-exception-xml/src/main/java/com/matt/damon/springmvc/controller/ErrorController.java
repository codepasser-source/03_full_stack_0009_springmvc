package com.matt.damon.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.matt.damon.springmvc.exception.MDException;

@Controller
@RequestMapping(value = "server")
public class ErrorController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "systemError.do", method = RequestMethod.GET)
	public ModelAndView systemError() throws Exception {
		logger.debug("systemError.do");

		String array[] = new String[] { "1", "2", "3" };
		System.out.println(array[3]);
		return new ModelAndView("/server/view");
	}

	@RequestMapping(value = "customError.do", method = RequestMethod.GET)
	public ModelAndView customError() throws Exception {
		logger.debug("customError.do");

		try {
			String array[] = new String[] { "1", "2", "3" };
			System.out.println(array[3]);
		} catch (Exception e) {
			MDException mdEx = new MDException();
			mdEx.setErrorCode("00001");
			mdEx.setMessage("数组下标越界了");
			throw mdEx;
		}

		return new ModelAndView("/server/view");
	}
}
