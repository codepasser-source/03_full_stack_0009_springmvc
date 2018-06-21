package com.mattdamon.core.exception;

/**
 * 
 * @author mattdamon
 *
 */
public class UserNotLoginException extends GeneralRuntimeException {

	private static final long serialVersionUID = -4699696761538260968L;

	public UserNotLoginException() {
	}

	public UserNotLoginException(String message) {
		super(message);
	}

	public UserNotLoginException(Throwable cause) {
		super(cause);
	}

	public UserNotLoginException(String message, Throwable cause) {
		super(message, cause);
	}
}
