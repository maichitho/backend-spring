package com.vietis.carpark.exception;

public class ParentNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ParentNotFoundException() {
	}

	public ParentNotFoundException(String message) {
		super(message);
	}

	public ParentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
