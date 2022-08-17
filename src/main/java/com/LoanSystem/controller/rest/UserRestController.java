package com.LoanSystem.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LoanSystem.dto.UserDto;
import com.LoanSystem.service.UserService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	UserService userService;
	
	@Operation(summary = "Find all user",description = "Get All Users",tags= {"user"})
	@GetMapping
	List<UserDto> all() {
		return userService.getAllUser();
	}
    @Operation(summary = "Get a user", description = "Get user by Id", tags = { "user" })
    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "successful fetch a user"),
	        @ApiResponse(responseCode = "404", description = "user not found")
	        })
	@GetMapping("/{id}")
	ResponseEntity<Object> getUser(@PathVariable Long id) {
		UserDto user = this.userService.getUserById(id);
		if (user.getId() != null) {
			return ResponseEntity.ok().body(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
		}

	}
    private ResponseEntity<Object> saveOrUpateUser(UserDto user, BindingResult result) {
		if(!result.hasErrors())
		{
			UserDto resultUser = this.userService.saveUser(user);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(resultUser);
		}
		else
		{
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(result.getAllErrors());
		}
	}
    
	@Operation(summary = "Save a User", description = "Save", tags = { "user" })
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "successful create a user",
	        		content = @Content(schema = @Schema(implementation = UserDto.class))),
	        @ApiResponse(responseCode = "400", description = "Validation error")
	        })
	@PostMapping
	ResponseEntity<Object> saveUser(@Valid @RequestBody UserDto user,
			BindingResult result) {
		return saveOrUpateUser(user, result);
	}

	
	@PutMapping("/{id}")
	ResponseEntity<Object> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto user,
			BindingResult result) {
		return saveOrUpateUser(user, result);
	}
	
	
	@Operation(summary = "Delete a User", description = "Delete", tags = { "user" })
	@DeleteMapping("/{id}")
	ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		UserDto user = this.userService.getUserById(id);
		if (user.getId() != null) {
			this.userService.deleteUserById(user.getId());
			return ResponseEntity.ok().body(user);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
		}

	}

}
