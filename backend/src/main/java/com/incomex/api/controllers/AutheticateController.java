package com.incomex.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incomex.api.data.in.UserIn;
import com.incomex.api.data.out.ResponseApi;
import com.incomex.api.exceptions.UnauthorizedException;
import com.incomex.api.services.UserService;

@RestController
@RequestMapping(path = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutheticateController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<Object> autheticate(@Valid @RequestBody UserIn userIn) throws UnauthorizedException {
		return ResponseApi.generateResponse(userService.validateUser(userIn));
	}

}
