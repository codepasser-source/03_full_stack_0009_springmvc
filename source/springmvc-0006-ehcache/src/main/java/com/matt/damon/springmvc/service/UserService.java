package com.matt.damon.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.matt.damon.springmvc.bean.UserBean;

@Service
public class UserService {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Cacheable(cacheName = "baseCache")
	public List<UserBean> list(String groupID) {

		logger.debug("UserService------>list");

		List<UserBean> users = new ArrayList<UserBean>();

		UserBean user = new UserBean();
		user.setName("tom");
		user.setPassword("cat");
		users.add(user);

		return users;
	}

	@TriggersRemove(cacheName = "baseCache", removeAll = true)
	public void modify(String groupID) {
		logger.debug("UserService------>modify");
	}
}
