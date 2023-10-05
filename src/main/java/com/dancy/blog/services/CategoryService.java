package com.dancy.blog.services;

import java.util.List;

import com.dancy.blog.entity.Category;
import com.dancy.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create Category
	CategoryDto createCategory(CategoryDto category);
	
	//updateCategory
	CategoryDto updateCategory(CategoryDto category, int id);
	
	//read Category
	List<CategoryDto> getAllCategories();
	
	//delete category
	void deleteCategory(int id);
	
	//fetch one category
	CategoryDto getCategoryById( int id);
	
	
	

}
