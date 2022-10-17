package com.incomex.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incomex.api.data.in.ProductIn;
import com.incomex.api.data.out.ResponseApi;
import com.incomex.api.exceptions.InvalidInputException;
import com.incomex.api.services.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public ResponseEntity<Object> listProducts() {
		return ResponseApi.generateResponse(productService.listProducts());
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Object> getProduct(@PathVariable("productId") Integer productId) {
		return ResponseApi.generateResponse(productService.getProduct(productId));
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductIn product) throws InvalidInputException {
		return ResponseApi.generateResponse(productService.createProduct(product));
	}
}
