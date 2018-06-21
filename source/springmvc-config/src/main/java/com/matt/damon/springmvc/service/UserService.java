package com.matt.damon.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.matt.damon.springmvc.bean.UserBean;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Cacheable(value = "andCache", key = "'list'+#groupID")
	public List<UserBean> list(String groupID) {

		logger.info("UserService------>list");

		List<UserBean> users = new ArrayList<UserBean>();

		UserBean user = new UserBean();
		user.setName("tom");
		user.setPassword("cat");
		users.add(user);

		return users;
	}

	@CacheEvict(value = "andCache", key = "'list'+#groupID")
	public void modify(String groupID) {
		logger.info("UserService------>modify");
	}
}
