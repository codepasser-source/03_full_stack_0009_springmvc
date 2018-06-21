package com.mattdamon.core.info;

import java.util.HashMap;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 
 * @author MATTDAMON
 * 
 */
public class CoreLogInterceptor {

	private static final String LOG_CONTENT_RESULT = "RESULT";
	private static final String LOG_CONTENT_ARGS = "ARGS";
	private static final String LOG_CONTENT_TARGET = "TARGET";
	private static final String LOG_CONTENT_PROCESSING = "PROCESSING";

	// controllerLog
	public Object controllerInterceptor(ProceedingJoinPoint pjp)
			throws Throwable {
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put(LOG_CONTENT_PROCESSING, "BEFOR");
		info.put(LOG_CONTENT_TARGET, pjp.getTarget().toString());
		CoreLogger.controllerLog(pjp.getTarget().getClass(), info);

		Object result = pjp.proceed();

		info.clear();
		info.put(LOG_CONTENT_PROCESSING, "END");
		info.put(LOG_CONTENT_TARGET, pjp.getTarget().toString());
		CoreLogger.controllerLog(pjp.getTarget().getClass(), info);
		return result;
	}

	// serviceLog
	public Object serviceInterceptor(ProceedingJoinPoint pjp) throws Throwable {
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put(LOG_CONTENT_PROCESSING, "BEFOR");
		info.put(LOG_CONTENT_TARGET, pjp.getTarget().toString());
		info.put(LOG_CONTENT_ARGS, pjp.getArgs());
		CoreLogger.serviceLog(pjp.getTarget().getClass(), info);

		Object result = pjp.proceed();

		info.clear();
		info.put(LOG_CONTENT_PROCESSING, "END");
		info.put(LOG_CONTENT_TARGET, pjp.getTarget().toString());
		info.put(LOG_CONTENT_RESULT, result);
		CoreLogger.serviceLog(pjp.getTarget().getClass(), info);
		return result;
	}
}