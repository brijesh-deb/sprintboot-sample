package com.example.sampleJsonXMLConverter.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;

	public ValidationException() {
		super();
	}

	public ValidationException(final String message) {
		super(message);
	}
}