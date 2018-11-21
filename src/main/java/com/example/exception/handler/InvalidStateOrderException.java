package com.example.exception.handler;

public class InvalidStateOrderException extends Exception {

	private static final long serialVersionUID = 2582722819300428882L;

	private String message;

	public InvalidStateOrderException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidStateOrderException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidStateOrderException [message=" + message + "]";
	}

}
