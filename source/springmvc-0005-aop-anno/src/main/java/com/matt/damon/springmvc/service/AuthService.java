package com.matt.damon.springmvc.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final Log logger = LogFactory.getLog(this.getClass());

	public void auth() {
		logger.debug("AuthService------>auth");
	}
}
