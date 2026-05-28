package com.wipro.bank.service;

import java.util.List;

import com.wipro.bank.dto.AccountDto;

public interface IAccountService {
	

	AccountDto createAccount(AccountDto dto);

	AccountDto getAccountById(int accountId);

	List<AccountDto> getAllAccounts();

	AccountDto updateAccount(int accountId, AccountDto dto);

	String deleteAccount(int accountId);

	double getBalance(String accountNumber);


}
