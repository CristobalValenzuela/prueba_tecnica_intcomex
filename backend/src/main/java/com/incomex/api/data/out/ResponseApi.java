package com.incomex.api.data.out;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.incomex.api.codes.ApiBaseCodes;

public class ResponseApi {

	private static final String OK = "OK";

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj, ApiBaseCodes code) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("code", code.getValue());
		map.put("message", message);
		map.put("data", responseObj);

		return new ResponseEntity<Object>(map, status);
	}
	
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, ApiBaseCodes code) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("code", code.getValue());
		map.put("message", message);

		return new ResponseEntity<Object>(map, status);
	}
	
	public static ResponseEntity<Object> generateResponse(Object responseObj) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("code", 0);
		map.put("message", OK);
		map.put("data", responseObj);

		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}
