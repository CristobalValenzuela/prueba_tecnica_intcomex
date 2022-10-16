package com.incomex.api.data.in;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductIn {

	@NotBlank(message = "Data is mandatory")
	private String productName;
	private Integer supplierId;
	@NotBlank(message = "Data is mandatory")
	private Integer categoryId;
	private Integer quantityPerUnit;
	private Integer unitPrice;
	private Integer unitsInStock;
	private Integer unitsOnOrder;
	private Integer reorderLevel;
	private Boolean discontinued;

}
