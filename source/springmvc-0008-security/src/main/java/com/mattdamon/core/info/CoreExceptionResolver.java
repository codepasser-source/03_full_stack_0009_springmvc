package com.mattdamon.core.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.mattdamon.core.exception.CoreException;

/**
 * 
 * @author MATTDAMON
 * 
 */
public class CoreExceptionResolver implements HandlerExceptionResolver {

	private static final String MAPPING_ACTION_REGEX = ".*\\.action";

	private static final String MAPPING_AJAX_REGEX = ".*\\.do";

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex) {

		String requestURL = request.getRequestURL().toString();

		CoreException exception = null;
		if (ex instanceof CoreException) {
			exception = (CoreException) ex;
		} else {
			exception = new CoreException(ex);
		}

		if (!"ERROR-11-000000".equals(exception.getCode())) {
			// 除未登录异常，其他异常记录log
			CoreLogger.systemLog(CoreExceptionResolver.class, exception);
		}

		if (mappingUrl(requestURL, MAPPING_ACTION_REGEX)) {
			// 跳转错误页面
			return new ModelAndView("/WEB-INF/view/error/500.jsp", "error",
					exception);
		} else if (mappingUrl(requestURL, MAPPING_AJAX_REGEX)) {
			return new ModelAndView("/WEB-INF/view/error/transfer.jsp",
					"errorJson", JSONObject.toJSONString(exception));
		} else {
			// 其他情况，默认跳转错误页面
			return new ModelAndView("/WEB-INF/view/error/500.jsp", "error",
					exception);
		}

	}

	protected boolean mappingUrl(String requestURL, String regex) {
		return requestURL.matches(regex);
	}
}
