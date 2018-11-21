package com.example.exception.handler;

public class InvalidDataException extends Exception {

	private static final long serialVersionUID = -5968916441926597623L;

	private String message;

	public InvalidDataException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidDataException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidDataException [message=" + message + "]";
	}

}
