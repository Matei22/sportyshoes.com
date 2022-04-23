package com.simplilearn.webservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	ExceptionResponse response;
	
	@ExceptionHandler(value=ProductNotFound.class)
	public ResponseEntity<ExceptionResponse> productNotFountException(ProductNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND.name(), new Date(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value=UserNotFound.class)
	public ResponseEntity<ExceptionResponse> UserNotFountException(UserNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND.name(), new Date(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=AdminNotFound.class)
	public ResponseEntity<ExceptionResponse> adminNotFound(AdminNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name(), new Date(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=ProductAlreadyExist.class)
	public ResponseEntity<ExceptionResponse> alreadyExistException(ProductAlreadyExist exception) {
		response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name(), new Date(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=UserAlreadyExist.class)
	public ResponseEntity<ExceptionResponse> userAlreadyExistException(UserAlreadyExist exception) {
		response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name(), new Date(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=NotAdmin.class)
	public ResponseEntity<ExceptionResponse> notAdminException(NotAdmin exception) {
		response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name(), new Date(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=UniqueAdminException.class)
	public ResponseEntity<ExceptionResponse> uniqueAdminException(UniqueAdminException exception) {
		response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name(), new Date(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(value=InvalidProductException.class)
	public ResponseEntity<ExceptionResponse> invalidException(InvalidProductException exception) {
		response = new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name(), new Date(), exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}
}
