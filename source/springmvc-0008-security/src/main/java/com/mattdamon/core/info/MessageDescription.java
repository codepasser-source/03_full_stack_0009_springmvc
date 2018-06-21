package com.mattdamon.core.info;

import java.text.DecimalFormat;

/**
 * 
 * @author MATTDAMON
 * 
 */
public final class MessageDescription implements MessageCode {

	private static final DecimalFormat FORMATTER = new DecimalFormat("00000000");
	private final String code;

	public static MessageDescription create(final int code) {
		return new MessageDescription(FORMATTER.format(code));
	}

	private MessageDescription(final String formatErrorCode) {
		this.code = formatErrorCode;
	}

	private MessageDescription() {
		// Default constructor should not be used.
		this(null);
	}

	public String getCode() {
		return code;
	}

	/********************** SYSTEM MESSAGE ***************************/

	/**
	 * 系统消息-操作成功。
	 */
	public static final MessageDescription MESSAGE_SUCCESS_1 = create(MESSAGE_SUCCESS);

	/**
	 * 系统消息-操作失败。
	 */
	public static final MessageDescription MESSAGE_FAILED_2 = create(MESSAGE_FAILED);

	/**
	 * 系统消息-登录成功。
	 */
	public static final MessageDescription MESSAGE_LOGIN_SUCCESS_1 = create(MESSAGE_LOGIN_SUCCESS);

	/**
	 * 系统消息-登录失败。
	 */
	public static final MessageDescription MESSAGE_LOGIN_FAILED_1 = create(MESSAGE_LOGIN_FAILED);

}
