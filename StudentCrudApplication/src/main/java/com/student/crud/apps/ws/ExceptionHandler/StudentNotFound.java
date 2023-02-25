package com.student.crud.apps.ws.ExceptionHandler;

public class StudentNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5950776722888715402L;

	public StudentNotFound(String message) {
		super(message);
	}
	
	

}
