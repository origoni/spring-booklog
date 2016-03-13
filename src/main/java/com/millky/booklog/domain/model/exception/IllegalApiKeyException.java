package com.millky.booklog.domain.model.exception;

public class IllegalApiKeyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1944124198941649796L;

	public IllegalApiKeyException() {
		super();
	}

	public IllegalApiKeyException(String message) {
		super(message);
	}

	public IllegalApiKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalApiKeyException(Throwable cause) {
		super(cause);
	}
}
