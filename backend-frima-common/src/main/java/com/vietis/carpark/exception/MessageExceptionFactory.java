package com.vietis.carpark.exception;


public class MessageExceptionFactory {
	
	public static RuntimeException getMessage(StatusCode ob, String mess) {
		ob.setMessage(mess);
		switch (ob) {
		case NOT_FOUND:
			return new ResourceNotFoundException(ob.getMessage());
		case NO_CONTENT:
			return new NoContentException(ob.getMessage());
		default:
			return new IntervalServerErrorException(ob.getMessage());	
		}
	}

	public static enum StatusCode {
		NOT_FOUND("Resource not found"), 
		NO_CONTENT("No content"), 
		INTERNAL_SERVER_ERROR("Internal server error"),
		CREATED("Creating successfully");
		private String message;

		StatusCode(String mess) {
			this.message = mess;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			if (message != null)
				this.message = message;
		}

	};

}
