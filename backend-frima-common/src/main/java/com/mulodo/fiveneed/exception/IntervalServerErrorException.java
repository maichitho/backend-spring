package com.mulodo.fiveneed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author DUONGND
 * @created 21/07/2017
 * 
 * @modified 
 * @modifier 
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class IntervalServerErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IntervalServerErrorException() {
	}

	public IntervalServerErrorException(String message) {
		super(message);
	}

	public IntervalServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}
