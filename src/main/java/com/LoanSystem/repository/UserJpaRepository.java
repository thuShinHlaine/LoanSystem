package com.LoanSystem.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostFilter;

import com.LoanSystem.dto.UserDto;
import com.LoanSystem.model.Role;
import com.LoanSystem.model.User;


public interface UserJpaRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>{
	User findByUserId(String userId);
	
}
