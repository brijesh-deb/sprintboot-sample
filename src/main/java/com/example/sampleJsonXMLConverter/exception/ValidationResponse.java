package com.example.sampleJsonXMLConverter.exception;

import java.util.List;

public class ValidationResponse {

	private String requestedURI;
	private List<String> errorMessages;

	public String getRequestedURI() {
		return requestedURI;
	}
	public void setRequestedURI(String requestedURI) {
		this.requestedURI = requestedURI;
	}
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
}
