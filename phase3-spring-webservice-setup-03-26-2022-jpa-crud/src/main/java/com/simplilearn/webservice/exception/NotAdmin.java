package com.simplilearn.webservice.exception;

public class NotAdmin extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotAdmin(String message){
		super(message);
	}
}
