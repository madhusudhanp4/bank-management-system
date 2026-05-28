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
	public AccountDto createAccount(AccountDto dto) {

		Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);

		if (customer == null) return null; //Customer doesnot exist

		// use mapper
		Account acc = AccountMapper.toEntity(dto, customer);

		// Generate account number automatically (user should not provide this)
		String accNumber = "ACC" + System.currentTimeMillis();
		acc.setAccountNumber(accNumber);

		Account saved = accountRepo.save(acc);

		return AccountMapper.toDto(saved);
	}


	//FETCH account details using account number
	@Override
	public AccountDto getAccountByNumber(String accountNumber) {

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null || "CLOSED".equals(acc.getStatus()))
			return null;

		return AccountMapper.toDto(acc);
	}


	//Fetch all active accounts
	@Override
	public List<AccountDto> getAllAccounts() {

		List<Account> list = accountRepo.findAll();
		List<AccountDto> result = new ArrayList<>();

		for (Account acc : list) {

			if ("CLOSED".equals(acc.getStatus()))
				continue;

			result.add(AccountMapper.toDto(acc)); 
		}

		return result;
	}


	//Get Current balance of account
	@Override
	public double getBalancebyNumber(String accountNumber) {

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null)
			return 0;

		return acc.getBalance();
	}


	//Update account details
	@Override
	public AccountDto updateAccountDetails(String accountNumber, AccountDto dto) {

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null || "CLOSED".equals(acc.getStatus()))
			return null;

		/*
		 * Since account already exists,
		 * update only required fields
		 * mapper is not used here to avoid overwriting existing data
		 * 
		 */

		acc.setAccountType(dto.getAccountType());
		acc.setBalance(dto.getBalance());
		acc.setBranchName(dto.getBranchName());

		Account updated = accountRepo.save(acc);

		return AccountMapper.toDto(updated);
	}


	//Close account
	@Override
	public String closeAccount(String accountNumber) {

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null)
			return "Account not found";

		if ("CLOSED".equals(acc.getStatus()))
			return "Already closed";

		acc.setStatus("CLOSED");

		accountRepo.save(acc);

		return "Account closed successfully ";
	}
}
