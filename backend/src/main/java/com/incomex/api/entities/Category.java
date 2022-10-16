package com.incomex.api.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue
	private Integer categoryId;
	@NotBlank(message = "Data is mandatory")
	private String categoryName;
	private String description;
	private String picture;
	@OneToMany
	@JoinColumn(name = "categoryId") 
	private Set<Product> products;
}
