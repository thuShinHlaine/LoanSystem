package com.LoanSystem.service;

import java.util.List;

import com.LoanSystem.dto.RoleDto;
import com.LoanSystem.dto.UserDto;
import com.LoanSystem.model.Role;


public interface UserService {

	List<UserDto> getAllUser();
	
	void deleteUserById(Long userId);

	UserDto saveUser(UserDto user);

	UserDto getUserById(Long id);

	
	

}
