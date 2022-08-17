package com.LoanSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.LoanSystem.dto.RoleDto;
import com.LoanSystem.dto.UserDto;
import com.LoanSystem.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Valid
@Controller
@Slf4j
@RequestMapping("/admin_role")
public class AdminController {

	@Autowired
	RoleService roleService;

	@GetMapping
	public String user(Model model) {

		List<RoleDto> role = roleService.getAllRole();
		model.addAttribute("role", role);
		UserDto adminRole = new UserDto();
		model.addAttribute("adminRole", adminRole);
		return "adminRole";
	}

}
