package com.incomex.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.incomex.api.codes.ApiBaseCodes;
import com.incomex.api.data.out.ResponseApi;
import com.incomex.api.exceptions.BusinessException;
import com.incomex.api.exceptions.InvalidInputException;

@ControllerAdvice
public class ExceptionHelper {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

	@ExceptionHandler(value = { InvalidInputException.class })
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
		logger.error("Invalid Input Exception: ", ex.getLocalizedMessage());
		return ResponseApi.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, ex.getCode());
	}

	@ExceptionHandler(value = { Unauthorized.class })
	public ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex) {
		logger.error("Unauthorized Exception: ", ex.getLocalizedMessage());
		return ResponseApi.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, ApiBaseCodes.getGenericCode());
	}

	@ExceptionHandler(value = { BusinessException.class })
	public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		logger.error("Business Exception: ", ex.getLocalizedMessage());
		return ResponseApi.generateResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getCode());
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		logger.error("Exception: ", ex);
		return ResponseApi.generateResponse(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ApiBaseCodes.getGenericCode());
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		logger.error("Method Argument Not Valid Exception: ", ex.getLocalizedMessage());
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseApi.generateResponse("Missing data", HttpStatus.BAD_REQUEST,errors, ApiBaseCodes.getMissingDataCode());
	}
}
