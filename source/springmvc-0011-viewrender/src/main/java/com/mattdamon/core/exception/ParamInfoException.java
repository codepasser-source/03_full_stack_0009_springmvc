package com.mattdamon.core.exception;

/**
 * @author mattdamon
 */
public class ParamInfoException extends GeneralRuntimeException {

	private static final long serialVersionUID = 3196735858881429614L;

	public ParamInfoException() {
	}

	public ParamInfoException(String message) {
		super(message);
	}

	public ParamInfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamInfoException(Throwable cause) {
		super(cause);
	}
}