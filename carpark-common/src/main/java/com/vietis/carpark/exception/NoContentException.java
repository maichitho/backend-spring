package com.vietis.carpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author DUONGND
 * @created 21/07/2017
 * 
 * @modified 
 * @modifier 
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoContentException() {
	}

	public NoContentException(String message) {
		super(message);
	}

	public NoContentException(String message, Throwable cause) {
		super(message, cause);
	}
}
