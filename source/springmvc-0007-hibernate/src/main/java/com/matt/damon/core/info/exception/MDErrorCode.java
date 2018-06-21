package com.matt.damon.core.info.exception;

public interface MDErrorCode {

	/**********************SYSTEM ERROR***************************/
	/**
	 * 系统错误-未知异常。
	 */
	int ERROR_OTHER = 10000000;

	/**
	 * 参数个数错误。
	 */
	int ERROR_METHOD_PARAMETER_NUMBER = 10000001;

	/**
	 * 参数值错误。
	 */
	int ERROR_METHOD_PARAMETER_VALUE = 10000002;

	/**
	 * 数据库异常.
	 */
	int ERROR_SQLEXCEPTION_OCCURRED = 10000003;

	/**********************CUSTOMER ERROR***************************/
	int ERROR_CUSTOMER_AUTHORITY = 11000000;
}
