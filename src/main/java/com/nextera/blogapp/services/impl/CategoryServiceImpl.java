package com.nextera.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextera.blogapp.entities.Category;
import com.nextera.blogapp.exceptions.ResourceNotFoundException;
import com.nextera.blogapp.payloads.CategoryDto;
import com.nextera.blogapp.respositories.CategoryRepo;
import com.nextera.blogapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedcategory = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedcategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat = categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		 Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		 this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories =this.categoryRepo.findAll();
		
		List<CategoryDto> catDto =categories.stream().map((cat) -> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}

}
