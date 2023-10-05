package com.dancy.blog.services;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dancy.blog.dao.CategoryRepository;
import com.dancy.blog.entity.Category;
import com.dancy.blog.exception.ResourceNotFoundException;
import com.dancy.blog.payloads.CategoryDto;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ModelMapper modelmapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelmapper.map(categoryDto,Category.class);
		Category cat =categoryRepository.save(category);
		return modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
		 Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category", "id", id));
	
		 category.setCategory(categoryDto.getCategory());
		 category.setCategoryDescription(categoryDto.getCategoryDescription());
		 categoryRepository.save(category);
		return modelmapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> cat = categoryRepository.findAll();
		System.out.println(cat.toString());
		List<CategoryDto> catDto = cat.stream().map(category -> this.categoryToDto(category) ).collect(Collectors.toList());
		System.out.println(catDto.toString());
		return catDto;
	}

	@Override
	public void deleteCategory(int id) {
		Category cat  = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id", id));
		categoryRepository.delete(cat);
	}

	@Override
	public CategoryDto getCategoryById(int id) {
		Category cat  = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id", id));
		return modelmapper.map(cat, CategoryDto.class);
	}
	
	public CategoryDto categoryToDto(Category cat)
	{
		return modelmapper.map(cat, CategoryDto.class);
	}

}
