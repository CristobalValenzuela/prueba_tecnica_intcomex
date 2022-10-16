package com.incomex.api.mappers;

import org.mapstruct.Mapper;

import com.incomex.api.data.in.CategoryIn;
import com.incomex.api.data.out.CategoryOut;
import com.incomex.api.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	Category categoryInToCategory(CategoryIn categoryIn);

	CategoryOut categoryToCategoryOut(Category category);
}
