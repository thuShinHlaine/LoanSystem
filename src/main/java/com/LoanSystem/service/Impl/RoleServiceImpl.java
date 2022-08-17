package com.LoanSystem.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LoanSystem.dto.RoleDto;
import com.LoanSystem.model.Role;
import com.LoanSystem.repository.RoleJpaRepository;
import com.LoanSystem.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired(required = true)
	ModelMapper mapper;

	@Autowired
	RoleJpaRepository RoleJpaRepository;

	private List<RoleDto> entityListToDto(Iterable<Role> Roles) {
		List<RoleDto> RoleDto = new ArrayList<RoleDto>();
		for (Role Role : Roles) {
			RoleDto dto = mapper.map(Role, RoleDto.class);
			RoleDto.add(dto);
		}

		return RoleDto;
	}

	@Override
	public List<RoleDto> getAllRole() {
		Iterable<Role> rolelist = this.RoleJpaRepository.findAll();
		return entityListToDto(rolelist);
	}

	

}
