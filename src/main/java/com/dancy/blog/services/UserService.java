package com.dancy.blog.services;

import java.util.List;

import com.dancy.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,int id);
	UserDto getUserById(int id);
	List<UserDto> getAllUsers();
	void deleteUser(int id);	
}
