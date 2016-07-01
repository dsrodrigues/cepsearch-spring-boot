package me.dsrodrigues.cepsearch.exception;

public class NotFoundZipCodeException extends RuntimeException {

	private static final long serialVersionUID = -8589088286537401462L;

	public NotFoundZipCodeException(String message) {
		super(message);
	}
}
