package com.student.crud.apps.ws.ExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler{
		
		//Specific Exception
		@ExceptionHandler(value = {StudentNotFound.class})
		public ResponseEntity<Object> handleUserServiceException(StudentNotFound ex, WebRequest req){
			
			ErrorMessage errorMessage = new ErrorMessage(new java.sql.Date(0),ex.getMessage(), HttpStatus.NOT_FOUND);  
			return new ResponseEntity<Object>(errorMessage,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		
		//all other exception
		@ExceptionHandler(value = {Exception.class})
		public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest req){
			
			ErrorMessage errorMessage = new ErrorMessage(new java.sql.Date(0),ex.getMessage(),HttpStatus.NOT_FOUND);  
			return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@Override
		protected ResponseEntity<Object> handleNoHandlerFoundException(
				NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

			ErrorMessage errorMessage = new ErrorMessage(new java.sql.Date(0),ex.getMessage(), HttpStatus.NOT_FOUND);  
			return new ResponseEntity<Object>(errorMessage,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		

}
