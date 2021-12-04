package com.challenge.rest.exceptions;

import org.springframework.http.HttpStatus;

public class UseCaseException extends RuntimeException {

	private final HttpStatus httpStatus;

	public UseCaseException(String message, HttpStatus httpStatus) {
		super( message );
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
}
