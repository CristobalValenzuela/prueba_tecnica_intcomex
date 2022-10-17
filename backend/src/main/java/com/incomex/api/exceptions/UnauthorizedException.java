package com.incomex.api.exceptions;

import com.incomex.api.codes.ApiBaseCodes;

import lombok.Getter;

public class UnauthorizedException extends Exception{

	private static final long serialVersionUID = -8476684811340160856L;

	@Getter
	private ApiBaseCodes code;
	
	public UnauthorizedException(Throwable exception, ApiBaseCodes code) {
		super(exception);
		this.code = code;
	}
	
	public UnauthorizedException(String error, ApiBaseCodes code) {
		super(error);
		this.code = code;
	}
	
}