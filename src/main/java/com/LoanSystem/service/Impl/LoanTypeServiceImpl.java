package com.LoanSystem.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LoanSystem.dto.LoanTypeDto;
import com.LoanSystem.model.LoanType;
import com.LoanSystem.repository.LoanTypeJpaRepository;
import com.LoanSystem.service.LoanTypeService;


@Service
public class LoanTypeServiceImpl implements LoanTypeService {

	@Autowired(required = true)
	ModelMapper mapper;

	@Autowired
	LoanTypeJpaRepository LoanTypeJpaRepository;

	private List<LoanTypeDto> entityListToDto(Iterable<LoanType> LoanTypes) {
		List<LoanTypeDto> LoanTypeDto = new ArrayList<LoanTypeDto>();
		for (LoanType LoanType : LoanTypes) {
			LoanTypeDto dto = mapper.map(LoanType, LoanTypeDto.class);
			LoanTypeDto.add(dto);
		}

		return LoanTypeDto;
	}

	@Override
	public List<LoanTypeDto> getAllLoanType() {
		Iterable<LoanType> loanlist = this.LoanTypeJpaRepository.findAll();
		return entityListToDto(loanlist);
	}

	

}
