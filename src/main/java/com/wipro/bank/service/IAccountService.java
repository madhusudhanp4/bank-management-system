package com.wipro.bank.service;

import java.util.List;

import com.wipro.bank.dto.AccountDto;

public interface IAccountService {

	// Open account
	String	createAccount(AccountDto dto);

	// Get account details (by account number - safer)
	AccountDto getAccount(String accountNumber);
	
	List<AccountDto> getAllAccounts();

	// Check balance
	double getBalancebyNumber(String accountNumber);

	String closeAccount(String accountNumber);


}

