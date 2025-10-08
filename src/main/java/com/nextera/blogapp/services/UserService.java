package com.nextera.blogapp.services;

import java.util.List;

import com.nextera.blogapp.payloads.UserDto;

public interface UserService {

	UserDto createUSer(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(Integer userId);
}
