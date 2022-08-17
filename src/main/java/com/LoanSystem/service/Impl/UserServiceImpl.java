package com.LoanSystem.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.LoanSystem.dto.RoleDto;
import com.LoanSystem.dto.UserDto;
import com.LoanSystem.model.Role;
import com.LoanSystem.model.User;
import com.LoanSystem.repository.UserJpaRepository;
import com.LoanSystem.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired(required = true)
	ModelMapper mapper;

	@Autowired
	UserJpaRepository userJpaRepository;

	private List<UserDto> entityListToDto(Iterable<User> Users) {
		List<UserDto> UserDto = new ArrayList<UserDto>();
		for (User User : Users) {
			UserDto dto = mapper.map(User, UserDto.class);
			UserDto.add(dto);
		}

		return UserDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		Iterable<User> userlist = this.userJpaRepository.findAll();
		return entityListToDto(userlist);
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		String password = userDto.getPassword();
		String encrytedPassword = encodePassword(password);

		System.out.println("Encoded Password: " + encrytedPassword);
		User user = mapUsertDtoToEntity(userDto);
		
		user = this.userJpaRepository.saveAndFlush(user);
		log.info("userID" + user.getId());
		return mapper.map(user, UserDto.class);
	}

	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	private User mapUsertDtoToEntity(UserDto userDto) {
		User user = mapper.map(userDto, User.class);

		return user;
	}

	@Override
	public UserDto getUserById(Long id) {
		Optional<User> patientOpt = this.userJpaRepository.findById(id);
		if (patientOpt.isPresent()) {
			User movie = patientOpt.get();
			UserDto dto = mapper.map(movie, UserDto.class);

			return dto;
		} else {
			return new UserDto();
		}
	}

	@Override
	public void deleteUserById(Long userId) {
	this.userJpaRepository.deleteById(userId);
		
	}

	
	

}
