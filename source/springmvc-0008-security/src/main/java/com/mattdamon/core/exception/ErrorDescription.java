package com.mattdamon.core.exception;

import java.text.DecimalFormat;

/**
 * 
 * @author MATTDAMON
 * 
 */
public final class ErrorDescription implements ErrorCode {

	private static final DecimalFormat FORMATTER = new DecimalFormat("00000000");
	private final String errorCode;

	public static ErrorDescription create(final int errorCode) {
		return new ErrorDescription(FORMATTER.format(errorCode));
	}

	private ErrorDescription(final String formatErrorCode) {
		this.errorCode = formatErrorCode;
	}

	private ErrorDescription() {
		// Default constructor should not be used.
		this(null);
	}

	public String getErrorCode() {
		return errorCode;
	}

	/********************** SYSTEM ERROR ***************************/

	/**
	 * 系统错误-未知异常。
	 */
	public static final ErrorDescription ERROR_OTHER_1 = create(ERROR_OTHER);

	/**
	 * 参数个数错误。
	 */
	public static final ErrorDescription ERROR_METHOD_PARAMETER_NUMBER_2 = create(ERROR_METHOD_PARAMETER_NUMBER);

	/**
	 * 参数值错误。
	 */
	public static final ErrorDescription ERROR_METHOD_PARAMETER_VALUE_2 = create(ERROR_METHOD_PARAMETER_VALUE);

	/**
	 * 数据库异常.
	 */
	public static final ErrorDescription ERROR_SQLEXCEPTION_OCCURRED_1 = create(ERROR_SQLEXCEPTION_OCCURRED);

	/**
	 * 网络连接异常.
	 */
	public static final ErrorDescription ERROR_HTTPEXCEPTION_OCCURRED_2 = create(ERROR_HTTPEXCEPTION_OCCURRED);

	/********************** CUSTOMER ERROR ***************************/
	/**
	 * 用户未登录
	 */
	public static final ErrorDescription ERROR_CUSTOMER_NOTLOGIN_0 = create(ERROR_CUSTOMER_NOTLOGIN);

	/**
	 * 用户登录失败
	 */
	public static final ErrorDescription ERROR_CUSTOMER_LOGINFAIL_1 = create(ERROR_CUSTOMER_LOGINFAIL);
	/**
	 * 权限认证异常.
	 */
	public static final ErrorDescription ERROR_CUSTOMER_AUTHORITY_0 = create(ERROR_CUSTOMER_AUTHORITY);

	/**
	 * 角色应用中
	 */
	public static final ErrorDescription ERROR_CUSTOMER_ROLE_HOLONOMIC_0 = create(ERROR_CUSTOMER_ROLE_HOLONOMIC);

	/**
	 * 用户名不唯一
	 */
	public static final ErrorDescription ERROR_CUSTOMER_USERNAME_UNI_1 = create(ERROR_CUSTOMER_USERNAME_UNI);

}
