package com.nextera.blogapp.services;

import java.util.List;

import com.nextera.blogapp.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
	void deleteCategory(Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategories();
}
