package com.mattdamon.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author mattdamon
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Server500Exception extends GeneralRuntimeException {

	private static final long serialVersionUID = -4315074017690836894L;

	public Server500Exception() {
	}

	public Server500Exception(String message) {
		super(message);
	}

	public Server500Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public Server500Exception(Throwable cause) {
		super(cause);
	}
}
