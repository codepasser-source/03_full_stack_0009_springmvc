package com.mattdamon.common.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mattdamon.core.env.CoreConfigProperties;
import com.mattdamon.core.env.CoreProperties;
import com.mattdamon.core.exception.CoreException;
import com.mattdamon.core.info.CoreLogger;
import com.whalin.MemCached.MemCachedClient;

/**
 * 
 * @author MATTDAMON
 * 
 */
public class SessionUtil {

	private static final String SESSION_MODE_LOCAL = "local";

	public static final String CLIENT_COOKIE_MSID = "neu-e-commerce-sid";

	public static final String USER_SESSION_KEY = "login_user";

	// static final String SESSION_CACHE_KEY = "xfsq-sid-";

	// 获取Session
	public static synchronized Serializable getSession(String name)
			throws CoreException {
		return getSession().get(name);
	}

	public static synchronized Map<String, Serializable> getSession()
			throws CoreException {

		CoreLogger.consoleLog(SessionUtil.class, "> msid:["
				+ getCookie(CLIENT_COOKIE_MSID) + "]");

		// 生产模式：保存在memcached中
		Map<String, Serializable> map = null;
		if (!isLocal()) {
			map = getMapFromMemcached();// not null
			return map;
		} else {
			// 开发模式：保存在本地session中
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();

			map = new HashMap<String, Serializable>();
			HttpSession session = request.getSession();
			@SuppressWarnings("rawtypes")
			Enumeration en = request.getSession().getAttributeNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				map.put(key, (Serializable) session.getAttribute(key));
			}
			return map;
		}
	}

	/**
	 * 获取session 数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized Map<String, Serializable> getMapFromMemcached() {
		Map<String, Serializable> map = null;
		String sessionId = getCookie(CLIENT_COOKIE_MSID);
		MemCachedClient client = getMemcachedClient();
		Object data = client.get(sessionId);
		if (data == null) {
			map = new HashMap<String, Serializable>();
			client.set(sessionId, map);
		} else {
			map = (Map<String, Serializable>) data;
		}
		return map;
	}

	/**
	 * 获取Memcached
	 * 
	 * @return
	 */
	public static synchronized MemCachedClient getMemcachedClient() {
		return (MemCachedClient) SpringUtils.getBean("memcachedClient");
	}

	/**
	 * 设置Session
	 * 
	 * @param name
	 * @param obj
	 * @throws CoreException
	 */
	public static synchronized void setSession(String name, Serializable obj)
			throws CoreException {
		// 生产模式：保存在memcached中
		if (!isLocal()) {
			Map<String, Serializable> map = getMapFromMemcached();
			map.put(name, obj);
			MemCachedClient client = getMemcachedClient();
			client.set(getCookie(CLIENT_COOKIE_MSID), map);
		} else {
			// 开发模式：保存在本地session中
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.setAttribute(name, obj);
		}
	}

	/**
	 * 清理session
	 * 
	 * @param name
	 * @throws CoreException
	 */
	public static synchronized void removeSession(String name)
			throws CoreException {

		if (!isLocal()) {
			Map<String, Serializable> map = getMapFromMemcached();
			map.remove(name);
			MemCachedClient client = getMemcachedClient();
			client.set(getCookie(CLIENT_COOKIE_MSID), map);
		} else {
			// 开发模式：保存在本地session中
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.removeAttribute(name);
		}
	}

	/**
	 * 清理session
	 * 
	 * @throws CoreException
	 */
	public static synchronized void clearSession() throws CoreException {
		// 生产模式：保存在memcached中
		if (!isLocal()) {
			Map<String, Serializable> map = getMapFromMemcached();
			if (map != null) {
				map.clear();
			}
			MemCachedClient client = getMemcachedClient();
			client.set(getCookie(CLIENT_COOKIE_MSID), map);
		} else {
			// 开发模式：保存在本地session中
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			@SuppressWarnings("rawtypes")
			Enumeration en = request.getSession().getAttributeNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				session.removeAttribute(key);
			}
		}
	}

	/**
	 * 获取 Session Model
	 * 
	 * @return
	 * @throws CoreException
	 */
	public static synchronized boolean isLocal() throws CoreException {

		CoreConfigProperties prop = CoreProperties
				.getProperties(CoreConfigProperties.class);

		String sessionMode = prop.getSessionMode();

		boolean isLocal = SESSION_MODE_LOCAL.equalsIgnoreCase(sessionMode);

		if (isLocal) {
			CoreLogger.consoleLog(SessionUtil.class, ">session:本地模式");
		} else {
			CoreLogger.consoleLog(SessionUtil.class, ">session:远程 memcached模式");
		}
		return isLocal;
	}

	/**
	 * 获取Cookie
	 * 
	 * @param key
	 * @return
	 */
	public static synchronized String getCookie(String key) {
		Cookie[] cookies = getCookie();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equalsIgnoreCase(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 获取Cookie
	 * 
	 * @param key
	 * @return
	 */
	public static synchronized String getCookie(HttpServletRequest request,
			String key) {
		Cookie[] cookies = getCookie(request);
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equalsIgnoreCase(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 获取Cookies
	 * 
	 * @return
	 */
	public static synchronized Cookie[] getCookie() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getCookies();
	}

	/**
	 * 获取Cookies
	 * 
	 * @return
	 */
	public static synchronized Cookie[] getCookie(HttpServletRequest request) {
		return request.getCookies();
	}

	/**
	 * 保存cookie
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @throws CoreException
	 */
	public static synchronized void setCookie(HttpServletResponse response,
			String key, String value) throws CoreException {
		setCookie(response, key, value, 0);
	}

	/**
	 * 保存cookie
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @param day
	 * @throws CoreException
	 */
	public static synchronized void setCookie(HttpServletResponse response,
			String key, String value, int day) throws CoreException {

		CoreConfigProperties prop = CoreProperties
				.getProperties(CoreConfigProperties.class);

		// value = URLEncoder.encode(value, "UTF-8");
		value = EscapeEncode.escape(value);
		Cookie cookie = new Cookie(key, value);
		cookie.setDomain(prop.getDomain());
		cookie.setPath("/");
		if (day > 0) {
			cookie.setMaxAge(60 * 60 * 24 * day);
		}
		response.addCookie(cookie);
	}

	public static synchronized void removeCookie(HttpServletResponse response,
			String key) {
		Cookie[] cookies = getCookie();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equalsIgnoreCase(cookies[i].getName())) {
					Cookie cookie = new Cookie(key, null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	public static synchronized boolean hasSession() throws CoreException {
		if (!isLocal()) {
			String msid = SessionUtil.getCookie(SessionUtil.CLIENT_COOKIE_MSID);
			if (StringUtils.isNullOrEmpty(msid)) {
				return false;
			}
		} else {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			if (session == null) {
				return false;
			}
		}
		return true;
	}
}
