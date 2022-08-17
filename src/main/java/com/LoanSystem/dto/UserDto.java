package com.LoanSystem.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.LoanSystem.model.Address;
import com.LoanSystem.model.Gender;
import com.LoanSystem.model.MaritalStatus;
import com.LoanSystem.model.Role;

import lombok.Data;

@Data
public class UserDto {
	private Long Id;
	
	@NotNull
	private String userId;

	@NotNull
	private String firstName;

	
	private String lastName;

	private String fullName;
	
	private String email;


	private Gender gender;

	@NotNull
	private String password;

	
	private Long loanAmount;


	
	private String loanType;

	
	private String homeOwner;

	
	private MaritalStatus maritalStatus;

	
	private String mobileNo;

	
	private String workPhone;

	private String occupation;

	
	private Long monthlyIncome;

	private Integer dependants;

	private Address address;
	
	private List<Role> roles;

	private String loanStatus;

	private Date createAt = new Date();
	
	private Date updateAt = new Date();

}
