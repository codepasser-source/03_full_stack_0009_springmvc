package com.mattdamon.core.exception;

/**
 * 
 * @author mattdamon
 *
 */
public class RenderException extends GeneralRuntimeException {

	private static final long serialVersionUID = 683377994664362172L;

	public RenderException() {

	}

	public RenderException(String message) {
		super(message);
	}

	public RenderException(String message, Throwable cause) {
		super(message, cause);
	}

	public RenderException(Throwable cause) {
		super(cause);
	}
}
