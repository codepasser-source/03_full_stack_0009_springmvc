package com.matt.damon.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {

	private final Log logger = LogFactory.getLog(this.getClass());

	private String mappingURL;

	public String getMappingURL() {
		return mappingURL;
	}

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		logger.debug("MDInterceptor -- > preHandle");
		String url = request.getRequestURL().toString();
		System.out.println(url);
		//ACL.checkACL(ACL.ROLE_ADMIN_ACL, ACL.createACL(ACL.PROJECT_CREATE));
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
		logger.debug("MDInterceptor -- > postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex)
			throws Exception {
		logger.debug("MDInterceptor -- > afterCompletion");

	}

}
