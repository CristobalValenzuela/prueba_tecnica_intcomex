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

import com.incomex.api.data.in.CategoryIn;
import com.incomex.api.data.out.ResponseApi;
import com.incomex.api.services.CategoryService;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public ResponseEntity<Object> listProducts() {
		return ResponseApi.generateResponse(categoryService.listCategories());
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<Object> getProduct(@PathVariable("categoryId") Integer categoryId) {
		return ResponseApi.generateResponse(categoryService.getCategory(categoryId));
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody CategoryIn category) {
		return ResponseApi.generateResponse(categoryService.createCategory(category));
	}
}
