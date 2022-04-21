package com.simplilearn.webservice.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private String message;
	private String status;
	private Date timestamp;
	private String cause;
	
	public ExceptionResponse(String message, String status, Date timestamp, String cause) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
		this.cause = cause;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	
}
