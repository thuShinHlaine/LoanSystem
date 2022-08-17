package com.LoanSystem.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	public String street;
	public String city;
	public String houseNumber;

}