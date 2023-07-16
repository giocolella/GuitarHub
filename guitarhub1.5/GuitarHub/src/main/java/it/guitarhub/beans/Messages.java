package it.guitarhub.beans;

public class Messages {
	private int statusCode;
	private String message;
	
	public Messages(int statusCode, String message) {
		this.setStatusCode(statusCode);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}