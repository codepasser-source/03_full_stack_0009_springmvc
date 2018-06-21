package com.matt.damon.springmvc.service;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matt.damon.hibernate.dao.UserDao;

@Service
@Transactional
public class UserService {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserDao userDao;

	public void saveUser() {

		logger.debug("UserService----->saveUser");
		userDao.save();
	}
}
