package com.ponleu.app.dto;

public class RestfulResponseHeader {
	
	private boolean result = true;
	private String message;
	private String messageKh;
	private String errorCode;
	
	public RestfulResponseHeader() {
	}

	public RestfulResponseHeader(boolean result, String errorCode) {
		this.result = result;
		this.errorCode = errorCode;
	}
	
	public RestfulResponseHeader(boolean result, String message, String messageKh, String errorCode) {
		this.result = result;
		this.message = message;
		this.messageKh = messageKh;
		this.errorCode = errorCode;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessageKh() {
		return messageKh;
	}

	public void setMessageKh(String messageKh) {
		this.messageKh = messageKh;
	}

}
