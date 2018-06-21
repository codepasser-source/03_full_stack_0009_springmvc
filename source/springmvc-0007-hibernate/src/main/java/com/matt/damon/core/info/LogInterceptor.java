package com.matt.damon.core.info;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogInterceptor {

	private final Log logger = LogFactory.getLog(this.getClass());

	public void before() {
		//TODO
		//System.out.println("method start...");
	}

	public void afterReturning() {
		//TODO
		//System.out.println("method after...");
	}

	public void afterThrowing() {
		//TODO
		//System.out.println("method afterThrowing...");
	}

	public void around(ProceedingJoinPoint pjp) throws Throwable {

		logger.debug(pjp.getClass() + "<->" + pjp.getTarget());
		pjp.proceed();
		System.out.println("method end...");
	}
}