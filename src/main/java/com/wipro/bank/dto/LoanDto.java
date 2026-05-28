package com.wipro.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoanDto {

	private int loanId;

	private String loanType;
	private double loanAmount;
	private double interestRate;
	
	private int customerId;


}
