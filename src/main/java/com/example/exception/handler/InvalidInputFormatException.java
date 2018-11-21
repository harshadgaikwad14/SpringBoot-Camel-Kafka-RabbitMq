package com.example.exception.handler;

public class InvalidInputFormatException extends Exception {

	private static final long serialVersionUID = -253304431095298913L;

	private String message;

	public InvalidInputFormatException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidInputFormatException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidInputFormatException [message=" + message + "]";
	}

}
