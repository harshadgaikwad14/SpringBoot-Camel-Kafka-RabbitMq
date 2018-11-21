package com.example.exception.handler;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = -1019374980870273656L;

	private String message;

	public DataNotFoundException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "DataNotFoundException [message=" + message + "]";
	}

}
