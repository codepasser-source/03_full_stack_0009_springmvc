package com.matt.damon.springmvc.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogInterceptor {

	public void before() {
		System.out.println("method start...");
	}

	public void afterReturning() {
		System.out.println("method after...");
	}

	public void afterThrowing() {
		System.out.println("method afterThrowing...");
	}

	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("method around...");
		System.out.println("method start...");
		Object retValue = pjp.proceed();
		System.out.println("method end...");
		return retValue;
	}
}