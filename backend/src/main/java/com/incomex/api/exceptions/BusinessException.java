package com.incomex.api.exceptions;

import com.incomex.api.codes.ApiBaseCodes;

import lombok.Getter;

public class BusinessException extends Exception{
	
	private static final long serialVersionUID = -1790987279406493503L;
	@Getter
	private ApiBaseCodes code;
	
	public BusinessException(Throwable exception, ApiBaseCodes code) {
		super(exception);
		this.code = code;
	}
	
	public BusinessException(String error, ApiBaseCodes code) {
		super(error);
		this.code = code;
	}
	
}
