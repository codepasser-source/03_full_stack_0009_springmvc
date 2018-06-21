package com.matt.damon.springmvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

public class MDExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex) {

		System.out.println(ex);
		//response.setStatus(HttpStatus.NOT_FOUND);
		if (ex instanceof MDException) {
			return new ModelAndView("/error/json", "error",
					JSONObject.toJSONString(ex));
		} else {
			return new ModelAndView("/error/500", "error", ex);
		}
	}

}
