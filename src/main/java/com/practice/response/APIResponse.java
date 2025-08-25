package com.practice.response;

import org.springframework.http.HttpStatus;

public class APIResponse<T> {

	private HttpStatus httpStatus;
	private String message;
	private Boolean status;
	private String target;
	private T response;

	public APIResponse(HttpStatus httpStatus, String message, Boolean status,String target, T response) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.status = status;
		this.target= target;
		this.response = response;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
