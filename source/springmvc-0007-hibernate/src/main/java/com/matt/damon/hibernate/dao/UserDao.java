package com.matt.damon.hibernate.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.matt.damon.hibernate.entity.UserEntity;

@Component
public class UserDao {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	public void save() {
		logger.debug("UserDao---->save");
		UserEntity user = new UserEntity();
		user.setAcl(1);
		user.setName("小名");
		user.setPassword("cat");
		user.setUsername("tomcat");
		getSession().save(user);

		logger.debug("saved");
	}
}
