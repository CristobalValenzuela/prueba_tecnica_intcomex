package com.incomex.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incomex.api.data.in.CategoryIn;
import com.incomex.api.data.out.CategoryOut;
import com.incomex.api.entities.Category;
import com.incomex.api.mappers.CategoryMapper;
import com.incomex.api.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryOut> listCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryOut> categoryOuts = new ArrayList<>();
		if(!categories.isEmpty()) {
			categories.stream().forEach( category -> categoryOuts.add(categoryMapper.categoryToCategoryOut(category)));
		}
		return categoryOuts;
	}
	
	public Category findById(Integer categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if(optionalCategory.isPresent()) {
			return optionalCategory.get();
		}
		return null;
	}

	public CategoryOut getCategory(Integer categoryId) {
		Category category = findById(categoryId);
		if(category != null) {
			return categoryMapper.categoryToCategoryOut(category);
		}
		return null;
	}

	public CategoryOut createCategory(CategoryIn categoryIn) {
		Category category = categoryMapper.categoryInToCategory(categoryIn);
		category = categoryRepository.save(category);
		return categoryMapper.categoryToCategoryOut(category);
	}

}
