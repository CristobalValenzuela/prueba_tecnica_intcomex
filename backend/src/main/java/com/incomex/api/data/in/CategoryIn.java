package com.incomex.api.data.in;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CategoryIn {
	
	@NotBlank(message = "Data is mandatory")
	private String categoryName;
	private String description;
	private String picture;

}
