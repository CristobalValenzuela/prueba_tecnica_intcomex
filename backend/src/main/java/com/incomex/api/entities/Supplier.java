package com.incomex.api.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "suppliers")
public class Supplier {

	@Id
	@GeneratedValue
	private Integer supplierId;
	private String companyName;
	private String contactName;
	private String contactTitle;
	private String address;
	private String city;
	private String region;
	private String postalCode;
	private String country;
	private Integer phone;
	private Integer fax;
	private String homePage;
	@OneToMany
	@JoinColumn(name = "supplierId") 
	private Set<Product> products;
}
