package com.LoanSystem.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LoanSystem.dto.LoanTypeDto;
import com.LoanSystem.dto.RoleDto;
import com.LoanSystem.dto.UserDto;
import com.LoanSystem.model.Role;
import com.LoanSystem.model.User;
import com.LoanSystem.repository.UserJpaRepository;
import com.LoanSystem.service.LoanTypeService;
import com.LoanSystem.service.RoleService;
import com.LoanSystem.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@Autowired
	RoleService roleService;
	
	@Autowired
	LoanTypeService loanTypeService;
	
	@Autowired
	UserJpaRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping 
	  public String home() {
		  log.info("Home Controller");
		  return "home"; 
		  }
	@GetMapping("/index")
	  public String index() {
		  log.info("Index Controller");
		  return "index"; 
		  }
	
	
	
	 @GetMapping("/login")
		String login(Model model,@RequestParam(value = "error",required = false) Boolean error){
			System.out.println("Login get");
			model.addAttribute("error", error);
			return "login";
		}
		@PostMapping("/login")
		String loginPost(Model model){
			System.out.println("Login post");

			
			return "login";
		}
		@GetMapping("/userAccountInfo")
		String page(Model model,UserDto applyLoans){
			 List<LoanTypeDto> loans = loanTypeService.getAllLoanType();
				model.addAttribute("loans", loans);
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
			    String currentUserName = authentication.getName();
			    
			    System.out.println("Loggined UserName "+currentUserName);
			    User user = this.userRepository.findByUserId(currentUserName);
			    model.addAttribute("user", user);
			   
				
			}
			return "userAccountInfo";
		}
		@GetMapping("/admin")
		String admin(Model model){
			
			List<UserDto> userList = this.userService.getAllUser( );
			
			model.addAttribute("userList", userList);	
		
			return "admin";
		}
		@GetMapping("/logoutSuccessful")
		String logoutSuccess(Model model)
		{
			System.out.println("You have been logout");
			return "index";
		}

}
