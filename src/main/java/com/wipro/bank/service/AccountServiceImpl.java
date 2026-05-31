package com.wipro.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.dto.AccountDto;
import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.mapper.AccountMapper;
import com.wipro.bank.repository.AccountRepository;
import com.wipro.bank.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements IAccountService {


	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private CustomerRepository customerRepo;


	//CREATE NEW ACCOUNT FOR EXISTING CUSTOMER
	@Override
	public String createAccount(AccountDto dto) {

		Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);

		if (customer == null) return null; //Customer doesn't exist

		// use mapper
		Account acc = AccountMapper.toEntity(dto, customer);

		// Generate account number automatically (user should not provide this)
		String accNumber = "ACC" + System.currentTimeMillis();
		acc.setAccountNumber(accNumber);

		Account saved = accountRepo.save(acc);

		AccountMapper.toDto(saved);
		
		return "Account created successfully";
	}


	//FETCH account details using account number
	@Override
	public AccountDto getAccount(String accountNumber) {

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null || "CLOSED".equals(acc.getStatus()))
			return null;

		return AccountMapper.toDto(acc);
	}


	//Fetch all active accounts
	@Override
	public List<AccountDto> getAllAccounts() {

		List<Account> accounts = accountRepo.findAll();
		List<AccountDto> dtoList = new ArrayList<>();

		for (Account acc : accounts) {

			dtoList.add(AccountMapper.toDto(acc));
		}

		return dtoList;
	}


	//Get Current balance of account
	@Override
	public double getBalancebyNumber(String accountNumber) {

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null)
			return 0;

		return acc.getBalance();
	}


	
	
	//Close account
	@Override
	public String closeAccount(String accountNumber) {

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null)
			return "Account not found";

		
		acc.setStatus("CLOSED");

		accountRepo.save(acc);

		return "Account closed successfully ";
	}
}
