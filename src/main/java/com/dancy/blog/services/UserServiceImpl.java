package com.dancy.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import com.dancy.blog.exception.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dancy.blog.dao.UserRepository;
import com.dancy.blog.entity.User;
import com.dancy.blog.payloads.UserDto;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = UserDtoToUser(userDto);
		User userObj = userRepository.save(user);
		UserDto dto = userToUserDto(userObj);
		return dto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {
		User user = userRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("user","id",id));
		
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setUserType(userDto.getUserType());
		user.setName(userDto.getName());
		User updatedUser =  userRepository.save(user);
		  UserDto dto = userToUserDto(updatedUser);		
		return dto;
	}
	
	@Override
	public UserDto getUserById(int id) {
		User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user", "id", id));
		return userToUserDto(user);
	}
		
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users	= userRepository.findAll();
		List<UserDto> userDtos= users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(int id) {
		User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user", "id", id));
		userRepository.delete(user);
	}
	
	
	//convert the Data Transfer Object to Entity Object
	public UserDto userToUserDto(User user)
	{
		UserDto dto = modelMapper.map(user, UserDto.class);
		/*
		 * dto.setEmail(user.getEmail()); dto.setAbout(user.getAbout());
		 * dto.setId(user.getId()); dto.setName(user.getName());
		 * dto.setPassword(user.getPassword()); dto.setUserType(user.getUserType());
		 */
		return dto;
	}
	
	//convert the Entity Object to Data Transfer Object 
	public User UserDtoToUser (UserDto dto)
	{
		User user = new User();
		user.setAbout(dto.getAbout());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setId(dto.getId());
		user.setUserType(dto.getUserType());
		user.setName(dto.getName());
		return user;
				
	}

	

}
