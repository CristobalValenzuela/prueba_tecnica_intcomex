package com.incomex.api.codes;

import lombok.Getter;

public enum CategoryCodes{

	WHITOUT_CATEGORY_NAME(ApiBaseCodes.WHITOUT_VALUE),
	NOT_FOUND_CATEGORY(ApiBaseCodes.NOT_FOUND);
	
	@Getter
	private ApiBaseCodes code;
	
	private CategoryCodes(int code) {
		this.code = new ApiBaseCodes(code);
	}
}
