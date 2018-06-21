package com.mattdamon.core.exception;

/**
 * 自定义【运行时】异常基类，有时间可扩展
 * 
 * @author mattdamon
 */
public class GeneralRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 5287205175840676327L;

	public GeneralRuntimeException() {
		super();
	}

	public GeneralRuntimeException(Throwable cause) {
		super(cause);
	}

	public GeneralRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneralRuntimeException(String message) {
		super(message);
	}
}
