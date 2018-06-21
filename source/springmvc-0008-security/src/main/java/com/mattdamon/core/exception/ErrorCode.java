package com.mattdamon.core.exception;

/**
 * 
 * @author MATTDAMON
 * 
 */
public interface ErrorCode {

	/********************** SYSTEM ERROR ***************************/
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

	/**
	 * 网络连接异常.
	 */
	int ERROR_HTTPEXCEPTION_OCCURRED = 10000004;

	/********************** CUSTOMER ERROR ***************************/

	/**
	 * 用户未登录
	 */
	int ERROR_CUSTOMER_NOTLOGIN = 11000000;

	/**
	 * 用户登录失败
	 */
	int ERROR_CUSTOMER_LOGINFAIL = 11000001;

	/**
	 * 权限不足
	 */
	int ERROR_CUSTOMER_AUTHORITY = 11000002;
	/**
	 * 角色应用中
	 */
	int ERROR_CUSTOMER_ROLE_HOLONOMIC = 11000003;
	/**
	 * 用户名不唯一
	 */
	int ERROR_CUSTOMER_USERNAME_UNI = 11000004;

}
