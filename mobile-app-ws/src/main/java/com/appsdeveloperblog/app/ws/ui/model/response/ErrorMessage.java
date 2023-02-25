package com.appsdeveloperblog.app.ws.ui.model.response;

import java.sql.Date;

public class ErrorMessage {
	private Date timestamp;
	private String messsage;

	public ErrorMessage() {
	}

	public ErrorMessage(Date timestamp, String messsage) {
		this.timestamp = timestamp;
		this.messsage = messsage;
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
