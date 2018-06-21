package com.mattdamon.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mattdamon.core.exception.RenderException;

/**
 * 根据requestURI返回渲染后的view
 */
@Controller
public class RenderMappingHandler {

	@Value("#{configProperties['web.site.domain']}")
	private String mainSite;

	@Value("#{configProperties['web.base.domain']}")
	private String domain;

	/**
	 * 渲染页面
	 *
	 * @param domain
	 *            domain
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @param context
	 *            context
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public void path(@RequestHeader("Host") String domain,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> context) {

		String path = request.getRequestURI().substring(
				request.getContextPath().length());

		// 如果在这里api请求如果还没被处理，抛出异常
		if (path.startsWith("/api/")) {
			throw new RenderException(
					"The current request is not yet realized ");
		}

		try {
			response.getWriter().write("defaultHandler view render");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
