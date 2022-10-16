package com.incomex.api.data.out;

import lombok.Data;

@Data
public class ProductOut {

	private Integer productId;
	private String productName;
	private Integer supplierId;
	private Integer quantityPerUnit;
	private Integer unitPrice;
	private Integer unitsInStock;
	private Integer unitsOnOrder;
	private Integer reorderLevel;
	private Boolean discontinued;
	private CategoryOut category;

}
