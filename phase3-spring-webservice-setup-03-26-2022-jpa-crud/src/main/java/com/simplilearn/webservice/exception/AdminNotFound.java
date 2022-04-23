package com.simplilearn.webservice.exception;

public class AdminNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AdminNotFound(String message){
		super(message);
	}
}
