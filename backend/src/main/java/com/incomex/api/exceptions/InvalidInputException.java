package com.incomex.api.exceptions;

import com.incomex.api.codes.ApiBaseCodes;

import lombok.Getter;

public class InvalidInputException extends Exception{

	private static final long serialVersionUID = 8791863908981886593L;
	@Getter
	private ApiBaseCodes code;
	
	public InvalidInputException(Throwable exception, ApiBaseCodes code) {
		super(exception);
		this.code = code;
	}
	
	public InvalidInputException(String error, ApiBaseCodes code) {
		super(error);
		this.code = code;
	}
	
}
