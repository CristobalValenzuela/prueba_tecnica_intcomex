package com.incomex.api.codes;

import lombok.Getter;

public enum SupplierCodes {

	NOT_FOUND_SUPPLIER(ApiBaseCodes.NOT_FOUND);
	
	@Getter
	private ApiBaseCodes code;
	
	private SupplierCodes(int code) {
		this.code = new ApiBaseCodes(code);
	}

}
