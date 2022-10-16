package com.incomex.api.mappers;

import org.mapstruct.Mapper;

import com.incomex.api.data.in.ProductIn;
import com.incomex.api.data.out.ProductOut;
import com.incomex.api.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	Product productInToProduct(ProductIn productIn);

	ProductOut productToProductOut(Product product);

}
