package com.matt.damon.springmvc.exception;

public class MDException extends Exception {

	private static final long serialVersionUID = -3812152708324492536L;
	private String errorCode;
	private String message;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MDException() {
		super();
	}

}
