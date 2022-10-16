package com.incomex.api.codes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiBaseCodes {
	
	public static int NOT_FOUND = 200;
	public static int WHITOUT_VALUE = 300;
	public static int GENERIC = 500;

	private int value;
	
	public static ApiBaseCodes getGenericCode() {
		return new ApiBaseCodes(GENERIC);
	}
	
	public static ApiBaseCodes getMissingDataCode() {
		return new ApiBaseCodes(WHITOUT_VALUE);
	}
}
