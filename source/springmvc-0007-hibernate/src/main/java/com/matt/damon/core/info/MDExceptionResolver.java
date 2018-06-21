package com.matt.damon.core.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.matt.damon.core.info.exception.MDException;

public class MDExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex) {

		String url = request.getRequestURL().toString();
		System.out.println(url);

		if (ex instanceof MDException) {
			return new ModelAndView("/error/error-json", "error",
					JSONObject.toJSONString(ex));
		} else {
			return new ModelAndView("/error/error", "error", ex);
		}
	}

}
