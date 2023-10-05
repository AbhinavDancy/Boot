package com.dancy.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dancy.blog.payloads.ApiResponses;
import com.dancy.blog.payloads.UserDto;
import com.dancy.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	//USer Creation
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		  UserDto createdUserDto = userService.createUser(userDto);
		
		return  new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
	}
	// fetch single user Details
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> fetchUserDetails(@PathVariable (value ="id") int id)
	{
		UserDto userDto	= userService.getUserById(id);
		return new ResponseEntity(userDto,HttpStatus.FOUND);
	}
	
	//fetch lists of user
	@GetMapping("/")
	public ResponseEntity<UserDto> getAllUsers()
	{
		List<UserDto> userDto = userService.getAllUsers();
		return new ResponseEntity(userDto,HttpStatus.FOUND);
	}
	//Update the User
	@PostMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") int id ,@RequestBody UserDto userDto)
	{
		 UserDto updatedUserDto =  userService.updateUser( userDto, id);
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
	}
	
	//Delete the User
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponses> deleteUser(@PathVariable("id")int idd)
	{
		userService.deleteUser(idd);
		return new ResponseEntity<>(new ApiResponses("User deleted Successfully",true),HttpStatus.OK);
	}
	
	
}
