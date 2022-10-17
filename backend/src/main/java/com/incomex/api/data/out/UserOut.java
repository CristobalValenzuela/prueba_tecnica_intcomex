package com.incomex.api.data.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(content = Include.NON_NULL)
public class UserOut {

	private Integer userId;
	private String name;
	private String token;

}
