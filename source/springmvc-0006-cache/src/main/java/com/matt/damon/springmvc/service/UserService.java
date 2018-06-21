package com.matt.damon.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.matt.damon.springmvc.bean.UserBean;

@Service
public class UserService {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Cacheable(value = "andCache", key = "'list'+#groupID")
	public List<UserBean> list(String groupID) {

		logger.debug("UserService------>list");

		List<UserBean> users = new ArrayList<UserBean>();

		UserBean user = new UserBean();
		user.setName("tom");
		user.setPassword("cat");
		users.add(user);

		return users;
	}

	@CacheEvict(value = "andCache", key = "'list'+#groupID")
	public void modify(String groupID) {
		logger.debug("UserService------>modify");
	}
}
