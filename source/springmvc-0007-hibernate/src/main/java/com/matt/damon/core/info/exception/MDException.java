package com.matt.damon.core.info.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MDException extends Exception {

	private static final long serialVersionUID = 4564704984419423245L;

	/**
	 * メッセージのデフォルトlocale.
	 */
	private Locale defaultLocale = Locale.getDefault();
	private static final String RESOURCE_NAME = "exception";
	private static final String MESSAGE_PREFIX = "ERROR";

	/**
	 * 分類名とメッセージ番号をつなぐ文字列.
	 */
	private static final String HEADER_DELIMITER = "-";
	/**
	 * メッセージ中のフィールドの区切りとなる文字列.
	 */
	private static final String MESSAGE_DELIMITER = ": ";

	/**
	 * メッセージの分類号. (例) 00 01 02...
	 */
	private String category = null;

	/**
	 * 10進6桁のメッセージ番号(文字列). (例) 000001
	 */
	private String errorCode = null;

	/**
	 * インスタンス生成.
	 * 
	 * @param errorDescription
	 *            エラーの定義
	 */
	public MDException(final MDErrorDescription errorDescription) {
		errorCode = errorDescription.getErrorCode().substring(2);
		category = errorDescription.getErrorCode().substring(0, 2);
	}

	/**
	 * インスタンス生成.
	 * 
	 * @param errorDescription
	 *            エラーの定義
	 * @param args
	 *            メッセージに渡す引数
	 */
	public MDException(final MDErrorDescription errorDescription,
			final Object... args) {
		errorCode = errorDescription.getErrorCode().substring(2);
		category = errorDescription.getErrorCode().substring(0, 2);
		arguments = args;
		if (args.length > 0) {
			if (args[0] instanceof MDException) {
				MDException exception = (MDException) args[0];
				errorCode = exception.errorCode;
				category = exception.category;
				arguments = exception.arguments;
			}
		}
	}

	/**
	 * インスタンス生成.
	 * 
	 * @param throwable
	 *            Throwable
	 */
	public MDException(final Throwable throwable) {
		this(MDErrorDescription.ERROR_OTHER_1, throwable);
	}

	/**
	 * 取得メッセージの分類号.
	 * 
	 * @return メッセージの分類号
	 */
	public final String getCategory() {
		return category;
	}

	/**
	 * エラーコードを取得する，分類号は含まれていません.
	 * 
	 * @return 异常号
	 */
	public final String getErrorCode() {
		return errorCode;
	}

	/**
	 * エラーメッセージの引数.
	 */
	private Object[] arguments;

	/**
	 * エラーコードの取得.
	 * 
	 * @return エラーコード
	 */
	public String getCode() {
		return MESSAGE_PREFIX + HEADER_DELIMITER + category + HEADER_DELIMITER
				+ errorCode;
	}

	/**
	 * エラーメッセージの取得.
	 * 
	 * @return エラーメッセージ
	 */
	public String getMessage() {
		return getLocalizedMessage();
	}

	/**
	 * 指定されたlocaleのResourceBundleの取得.
	 */
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

	/**
	 * 取得异常メッセージ.
	 * 
	 * @return 异常メッセージ
	 */
	public final String getLocalizedMessage() {
		return getLocalizedMessage(getDefaultLocale());
	}

	/**
	 * 指定されたLocaleのメッセージがあれば返す。(言語が一致すれば可。). なければnullを返す。
	 * 
	 * @param locale
	 *            言語環境
	 * @return 异常メッセージ
	 */
	public final String getLocalizedMessage(final Locale locale) {
		ResourceBundle resourceBundle = this.getBundle(locale);
		String resouceBundleLanguage = resourceBundle.getLocale().getLanguage();

		/*
		 * 取得したリソースバンドルがlocale指定なしの場合、VMのlocaleが指定されたものとみなす。
		 * (本来は、サービスのlocaleを見るべき。)
		 */
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
		message += errorCode;
		String key = message;// ERROR-10000001
		message += MESSAGE_DELIMITER;
		String detailFormatString = null;
		detailFormatString = resourceBundle.getString(key);
		message += MessageFormat.format(detailFormatString, arguments);
		return message;
	}

	/**
	 * <code>defaultLocale</code>に設定されたlocaleを返す.
	 * 
	 * @return 言語環境
	 */
	public final Locale getDefaultLocale() {
		return this.defaultLocale;
	}

}
