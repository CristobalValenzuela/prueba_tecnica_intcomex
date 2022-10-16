package com.incomex.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incomex.api.codes.CategoryCodes;
import com.incomex.api.codes.SupplierCodes;
import com.incomex.api.data.in.ProductIn;
import com.incomex.api.data.out.ProductOut;
import com.incomex.api.entities.Product;
import com.incomex.api.exceptions.InvalidInputException;
import com.incomex.api.mappers.ProductMapper;
import com.incomex.api.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private CategoryService categoryService;

	public List<ProductOut> listProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductOut> productOuts = new ArrayList<>(); 
		if(!products.isEmpty()) {
			products.stream().forEach(product -> productOuts.add(productMapper.productToProductOut(product)));
		}
		return productOuts;
	}

	public ProductOut getProduct(Integer productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			ProductOut productOut = productMapper.productToProductOut(product);
			productOut.setCategory(categoryService.getCategory(product.getCategoryId()));
			return productOut;
		}
		return null;		
	}

	public ProductOut createProduct(ProductIn productIn) throws InvalidInputException {
		Product product = productMapper.productInToProduct(productIn);
		if(productIn.getSupplierId() != null && supplierService.findById(productIn.getSupplierId()) == null) {
			throw new InvalidInputException("Supplier id not found", SupplierCodes.NOT_FOUND_SUPPLIER.getCode());
		}
		if(productIn.getCategoryId() != null && categoryService.findById(productIn.getCategoryId()) == null) {
			throw new InvalidInputException("Category id not found", CategoryCodes.NOT_FOUND_CATEGORY.getCode());
		}
		product = productRepository.save(product);
		return productMapper.productToProductOut(product);
	}
	
	
}
