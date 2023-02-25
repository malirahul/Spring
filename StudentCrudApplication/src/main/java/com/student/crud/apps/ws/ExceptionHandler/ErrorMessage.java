package com.student.crud.apps.ws.ExceptionHandler;

import java.sql.Date;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	private Date timestamp;
	private String messsage;
	private HttpStatus httpStatus;

	public ErrorMessage() {
	}

	public ErrorMessage(Date timestamp, String messsage, HttpStatus httpStatus) {
		super();
		this.timestamp = timestamp;
		this.messsage = messsage;
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

}
