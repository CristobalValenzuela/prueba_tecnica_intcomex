package com.incomex.api.data.in;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserIn {

	@NotBlank(message = "Data is mandatory")
	private String name;
	@NotBlank(message = "Data is mandatory")
	private String password;
}
