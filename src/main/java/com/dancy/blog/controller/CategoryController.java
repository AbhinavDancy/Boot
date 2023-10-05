package com.dancy.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dancy.blog.payloads.ApiResponses;
import com.dancy.blog.payloads.CategoryDto;
import com.dancy.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	
	@Autowired
	CategoryService categoryService;
	//create Category 
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto )
	{
		 CategoryDto dto = categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
		
	}
	
	//read all users
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> dto = categoryService.getAllCategories();
		return  new ResponseEntity<>(dto,HttpStatus.FOUND);
		
	}
	
	//update a Category
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto ,@PathVariable("id") int id)
	{
		 CategoryDto dto = categoryService.updateCategory(categoryDto, id);
		
		return new ResponseEntity<>(dto,HttpStatus.FOUND);
		
	}
	
	//delete a Category
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponses> deleteCategory(@PathVariable int id)
	{	
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(new ApiResponses("Deletion Successfull",true),HttpStatus.OK) ;
	}
	
	//fetch a single Category detail
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> fetchById(@PathVariable("id") int id)
	{
		CategoryDto dto = categoryService.getCategoryById(id);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	

}
