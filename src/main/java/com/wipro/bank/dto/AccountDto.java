package com.wipro.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AccountDto {

	private int accountId;

	private String accountNumber;
	private String accountType;
	private double balance;
	private String branchName;
	
	private int customerId;





}
