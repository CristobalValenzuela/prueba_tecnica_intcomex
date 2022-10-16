package com.incomex.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue
	private Integer productId;
	private String productName;
	private Integer supplierId;
	private Integer categoryId;
	private Integer quantityPerUnit;
	private Integer unitPrice;
	private Integer unitsInStock;
	private Integer unitsOnOrder;
	private Integer reorderLevel;
	private Boolean discontinued;
	@ManyToOne
	@JoinColumn(name="categoryId", insertable = false, updatable = false)
	private Category category;
}
