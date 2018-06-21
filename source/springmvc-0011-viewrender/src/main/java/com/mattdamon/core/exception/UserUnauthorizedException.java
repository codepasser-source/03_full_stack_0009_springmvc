package com.mattdamon.core.exception;

/**
 * 
 * @author mattdamon
 *
 */
public class UserUnauthorizedException extends GeneralRuntimeException {

	private static final long serialVersionUID = -7638959290531908614L;

	public UserUnauthorizedException() {
	}

	public UserUnauthorizedException(String message) {
		super(message);
	}

	public UserUnauthorizedException(Throwable cause) {
		super(cause);
	}

	public UserUnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
}
