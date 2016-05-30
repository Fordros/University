package com.university.exception;

public class DomainException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public DomainException(){

	}

	public DomainException(String message) {
		super(message);
	}

	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}
}
