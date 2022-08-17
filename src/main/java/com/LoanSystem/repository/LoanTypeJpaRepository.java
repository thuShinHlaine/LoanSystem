package com.LoanSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LoanSystem.model.LoanType;


public interface LoanTypeJpaRepository extends JpaRepository<LoanType, Long>{

}
