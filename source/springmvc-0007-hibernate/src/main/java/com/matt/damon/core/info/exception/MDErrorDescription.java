package com.matt.damon.core.info.exception;

import java.text.DecimalFormat;

public final class MDErrorDescription implements MDErrorCode {

	private static final DecimalFormat FORMATTER = new DecimalFormat("00000000");
	private final String errorCode;

	/**
	 * 指定のエラーコードで作成する.
	 * 
	 * @param errorCode
	 *            情報コード
	 * @return 作成されたCoreErrorDescription
	 */
	public static MDErrorDescription create(final int errorCode) {
		return new MDErrorDescription(FORMATTER.format(errorCode));
	}

	private MDErrorDescription(final String formatErrorCode) {
		this.errorCode = formatErrorCode;
	}

	private MDErrorDescription() {
		// Default constructor should not be used.
		this(null);
	}

	/**
	 * 情報コードの文字列.
	 * 
	 * @return エラーコード
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**********************SYSTEM ERROR***************************/

	/**
	 * 系统错误-未知异常。
	 */
	public static final MDErrorDescription ERROR_OTHER_1 = create(ERROR_OTHER);

	/**
	 * 参数个数错误。
	 */
	public static final MDErrorDescription ERROR_METHOD_PARAMETER_NUMBER_2 = create(ERROR_METHOD_PARAMETER_NUMBER);

	/**
	 * 参数值错误。
	 */
	public static final MDErrorDescription ERROR_METHOD_PARAMETER_VALUE_2 = create(ERROR_METHOD_PARAMETER_VALUE);

	/**
	 * 数据库异常.
	 */
	public static final MDErrorDescription ERROR_SQLEXCEPTION_OCCURRED_1 = create(ERROR_SQLEXCEPTION_OCCURRED);

	/**********************CUSTOMER ERROR***************************/
	/**
	 * 权限认证异常.
	 */
	public static final MDErrorDescription ERROR_CUSTOMER_AUTHORITY_0 = create(ERROR_CUSTOMER_AUTHORITY);
}
