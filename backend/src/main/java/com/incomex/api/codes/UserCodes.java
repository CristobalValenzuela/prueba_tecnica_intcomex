package com.incomex.api.codes;

import lombok.Getter;

public enum UserCodes {

	NOT_FOUND_USER(ApiBaseCodes.NOT_FOUND);
	
	@Getter
	private ApiBaseCodes code;
	
	private UserCodes(int code) {
		this.code = new ApiBaseCodes(code);
	}

}
