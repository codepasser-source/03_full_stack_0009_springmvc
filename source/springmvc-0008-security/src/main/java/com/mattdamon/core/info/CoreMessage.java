package com.mattdamon.core.info;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author MATTDAMON
 * 
 */
public class CoreMessage implements Serializable {

	private static final long serialVersionUID = -7685912132974520827L;

	private Locale defaultLocale = Locale.CHINA;
	private static final String RESOURCE_NAME = "message";
	private static final String MESSAGE_PREFIX = "MSG";
	private static final String HEADER_DELIMITER = "-";
	private static final String MESSAGE_DELIMITER = ": ";
	// 消息类型
	private String category = null;
	// 消息代码
	private String code = null;
	// 消息代码
	private String msgCode = null;
	private Object data = null;

	public CoreMessage(final MessageDescription msgDescription) {
		code = msgDescription.getCode().substring(2);
		msgCode = code;
		category = msgDescription.getCode().substring(0, 2);
	}

	public CoreMessage(final MessageDescription msgDescription,
			Object clientData) {
		code = msgDescription.getCode().substring(2);
		msgCode = code;
		category = msgDescription.getCode().substring(0, 2);
		data = clientData;
	}

	private Object[] arguments;

	public CoreMessage(final MessageDescription msgDescription,
			final Object... args) {
		code = msgDescription.getCode().substring(2);
		msgCode = code;
		category = msgDescription.getCode().substring(0, 2);

		arguments = args;
		if (args.length > 0) {
			if (args[0] instanceof CoreMessage) {
				CoreMessage message = (CoreMessage) args[0];
				code = message.code;
				category = message.category;
				arguments = message.arguments;
			}
		}
	}

	public CoreMessage(final MessageDescription msgDescription,
			Object clientData, final Object... args) {
		code = msgDescription.getCode().substring(2);
		msgCode = code;
		category = msgDescription.getCode().substring(0, 2);
		data = clientData;

		arguments = args;
		if (args.length > 0) {
			if (args[0] instanceof CoreMessage) {
				CoreMessage message = (CoreMessage) args[0];
				code = message.code;
				category = message.category;
				arguments = message.arguments;
			}
		}
	}

	public String getMsgCode() {
		return msgCode;
	}

	public final String getCategory() {
		return category;
	}

	public String getCode() {
		return MESSAGE_PREFIX + HEADER_DELIMITER + category + HEADER_DELIMITER
				+ code;
	}

	public String getMessage() {
		return getLocalizedMessage();
	}

	private ResourceBundle getBundle(final Locale locale) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(getBaseName(),
				locale);
		if (resourceBundle.getLocale().getLanguage()
				.equals(locale.getLanguage())) {
			return resourceBundle;
		}

		return ResourceBundle.getBundle(getBaseName());
	}

	private String getBaseName() {
		return RESOURCE_NAME;
	}

	public final String getLocalizedMessage() {
		return getLocalizedMessage(getDefaultLocale());
	}

	public final String getLocalizedMessage(final Locale locale) {
		ResourceBundle resourceBundle = this.getBundle(locale);
		String resouceBundleLanguage = resourceBundle.getLocale().getLanguage();

		if (resouceBundleLanguage.length() == 0) {
			resouceBundleLanguage = Locale.getDefault().getLanguage();
		}

		if (!resouceBundleLanguage.equals(locale.getLanguage())) {
			return null;
		}
		String message = "";
		message += MESSAGE_PREFIX;
		message += HEADER_DELIMITER;
		message += category;
		message += HEADER_DELIMITER;
		message += code;
		String key = message;// MSG-00-000000
		message += MESSAGE_DELIMITER;
		String detailFormatString = null;
		detailFormatString = resourceBundle.getString(key);
		message += MessageFormat.format(detailFormatString, arguments);
		return message;
	}

	public final Locale getDefaultLocale() {
		return this.defaultLocale;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}