package com.ponleu.app.dto;

public class RestfulResponse {
	private RestfulResponseHeader header;
	private Object body;

	public RestfulResponse() {
	}

	public RestfulResponse(RestfulResponseHeader header, Object body) {
		this.header = header;
		this.body = body;
	}

	public RestfulResponseHeader getHeader() {
		return header;
	}

	public void setHeader(RestfulResponseHeader header) {
		this.header = header;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
