package com.simplilearn.webservice.exception;

public class UniqueAdminException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UniqueAdminException(String message){
		super(message);
	}
}
