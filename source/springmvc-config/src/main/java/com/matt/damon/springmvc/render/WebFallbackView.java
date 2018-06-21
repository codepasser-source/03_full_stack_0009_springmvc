package com.matt.damon.springmvc.render;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

/**
 * Created by IntelliJ IDEA. User: AnsonChan Date: 14/10/15
 */
@Controller
public class WebFallbackView {

	@Value("${app.domain}")
	private String siteDomain;

	private static Set<String> platformDomainPrefix = ImmutableSet
			.<String> builder().add("member.").add("order.").add("bc.")
			.add("passport.").add("pay.").build();

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public void render(@RequestHeader("Host") String domain,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> context) {

		prepareContext(request, context);

		String path = request.getRequestURI().substring(
				request.getContextPath().length());

		System.out.println(path);

		domain = domain.split(":")[0];

		for (String prefix : platformDomainPrefix) {
			if (domain.startsWith(prefix)) { // 如果是属于主站的页面
				System.out.println(prefix);
				break;
			}
		}
		System.out.println(domain);
		context.put("seo", "title");
		response.setContentType(com.google.common.net.MediaType.HTML_UTF_8
				.toString());
		// response.setContentLength(html.getBytes(Charsets.UTF_8).length);
		try {
			response.getWriter().write("<body>GET VIEW</body>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	public void renderPost(@RequestHeader("Host") String domain,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> context) {

		String path = request.getRequestURI().substring(
				request.getContextPath().length());

		System.out.println(path);

		domain = domain.split(":")[0];

		for (String prefix : platformDomainPrefix) {
			if (domain.startsWith(prefix)) { // 如果是属于主站的页面
				System.out.println(prefix);
				break;
			}
		}
		System.out.println(domain);
		context.put("seo", "title");
		response.setContentType(com.google.common.net.MediaType.HTML_UTF_8
				.toString());
		// response.setContentLength(html.getBytes(Charsets.UTF_8).length);
		try {
			response.getWriter().write("<body>GET VIEW</body>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void prepareContext(HttpServletRequest request,
			Map<String, Object> context) {
		if (request != null) {
			for (Object name : request.getParameterMap().keySet()) {
				context.put((String) name, request.getParameter((String) name));
			}
			// get regionId from cookies
			Cookie[] cookies = request.getCookies();
			String region = null;
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (Objects.equal(cookie.getName(), "JSESSIONID")) {
						try {
							region = cookie.getValue();
						} catch (NumberFormatException e) {
							// ignore this
						}
					}
				}
			}
			if (region != null) {
				context.put("JSESSIONID", region);
			}
		}
		context.put("USER_KEY", "user01");
	}
}
