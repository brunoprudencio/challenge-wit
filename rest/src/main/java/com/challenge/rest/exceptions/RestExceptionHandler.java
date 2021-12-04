package com.challenge.rest.exceptions;

import java.net.ConnectException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String CONNECT_EXCEPTION = "Service unavailable";

	@ExceptionHandler({ UseCaseException.class })
	public ResponseEntity<Object> handleException(UseCaseException useCaseException,
			WebRequest request) {
		return handleExceptionInternal( useCaseException, useCaseException.getMessage(),
				new HttpHeaders(), useCaseException.getHttpStatus(), request );
	}

	@ExceptionHandler({ ConnectException.class })
	public ResponseEntity<Object> handleConnectException(ConnectException connectException,
			WebRequest request) {
		return handleExceptionInternal( connectException, CONNECT_EXCEPTION,
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request );
	}
}

