package com.incomex.api.exceptions;

public class UnauthorizedException extends Exception{

	private static final long serialVersionUID = -8476684811340160856L;

	public UnauthorizedException(Throwable exception) {
		super(exception);
	}
	
}