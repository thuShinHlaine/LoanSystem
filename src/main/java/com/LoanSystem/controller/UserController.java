package com.LoanSystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.LoanSystem.dto.LoanTypeDto;
import com.LoanSystem.dto.RoleDto;
import com.LoanSystem.dto.UserDto;
import com.LoanSystem.service.LoanTypeService;
import com.LoanSystem.service.RoleService;
import com.LoanSystem.service.UserService;


import lombok.extern.slf4j.Slf4j;

@Valid
@Controller
@Slf4j
@RequestMapping("/apply_loan")
public class UserController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	LoanTypeService loanTypeService;
	
	@Autowired
	UserService userService;
	
	public void getAllModel(Model model) {
		List<RoleDto> role = roleService.getAllRole();
		model.addAttribute("role", role);
		
		List<LoanTypeDto> loans = loanTypeService.getAllLoanType();
		model.addAttribute("loans", loans);
		System.out.println(loans);
	}
	
	@GetMapping
	public String user(Model model) {

		getAllModel(model);
		UserDto applyLoans = new UserDto();
		model.addAttribute("applyLoans", applyLoans);
		return "apply";
	}
	
	@PostMapping
	public String createOrUpdateUser(@Valid @ModelAttribute("applyLoans") UserDto applyLoans, Errors errors,
			RedirectAttributes redirectAttrs, HttpSession session,Model model) {

		try {
			if (errors.hasErrors()) {
			
				getAllModel(model);
				return "apply";
			} else {
				applyLoans = this.userService.saveUser(applyLoans);
				redirectAttrs.addAttribute("userId", applyLoans.getId()).addFlashAttribute("message",
						"Account created!");
				log.info("Loan Controller has " + applyLoans.getId());

				return "redirect:login";
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("error");
			return "apply";
		}

	}
	
	@GetMapping("view/{id}")
	public String viewUser(@PathVariable Long id ,Model model) {
		log.info("View User "+id);
		getAllModel(model);
		UserDto applyLoans = this.userService.getUserById(id);
				
		//UserDto applyLoans = new UserDto();
		model.addAttribute("applyLoans", applyLoans);
		return "view";
	}

}
