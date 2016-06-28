package me.dsrodrigues.cepsearch.exception;

public class InvalidZipCodeException extends RuntimeException {

	private static final long serialVersionUID = -8589088286537401462L;

	public InvalidZipCodeException(String message) {
		super(message);
	}
}
