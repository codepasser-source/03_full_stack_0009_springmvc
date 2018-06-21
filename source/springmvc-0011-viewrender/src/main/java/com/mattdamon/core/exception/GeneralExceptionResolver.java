package com.mattdamon.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * GeneralExceptionResolver.java
 *
 * @author <A>cheng.yy@neusoft.com</A>
 *
 * @date May 5, 2015
 *
 * @Copyright: © 2001-2015 东软集团股份有限公司
 *
 */
public class GeneralExceptionResolver implements HandlerExceptionResolver {

	private static final String MAPPING_ACTION_REGEX = ".*\\.action";

	private static final String MAPPING_AJAX_REGEX = ".*\\.do";

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex) {

		String requestURL = request.getRequestURL().toString();
		System.out.println(requestURL);
		ex.printStackTrace();
		GeneralException exception = null;
		if (ex instanceof GeneralException) {
			exception = (GeneralException) ex;
		} else {
			exception = new GeneralException(ex);
		}

		if (mappingUrl(requestURL, MAPPING_ACTION_REGEX)) {
			return new ModelAndView("/WEB-INF/jsp/error/500.jsp", "error",
					JSONObject.toJSONString(exception));
		} else if (mappingUrl(requestURL, MAPPING_AJAX_REGEX)) {
			return new ModelAndView("/WEB-INF/jsp/error/transfer.jsp",
					"errorJson", JSONObject.toJSONString(exception));
		} else {
			return new ModelAndView("/WEB-INF/jsp/error/500.jsp", "error",
					JSONObject.toJSONString(exception));
		}

	}

	protected boolean mappingUrl(String requestURL, String regex) {
		return requestURL.matches(regex);
	}
}
