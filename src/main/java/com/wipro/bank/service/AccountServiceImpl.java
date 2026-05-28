package com.wipro.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.dto.AccountDto;
import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.repository.AccountRepository;
import com.wipro.bank.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements IAccountService {


	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public AccountDto createAccount(AccountDto dto) {
		// TODO Auto-generated method stub

		Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);

		if (customer == null) return null;

		Account acc = new Account();

		acc.setAccountNumber(dto.getAccountNumber());
		acc.setAccountType(dto.getAccountType());
		acc.setBalance(dto.getBalance());
		acc.setBranchName(dto.getBranchName()); 

		Account saved = accountRepo.save(acc);

		dto.setAccountId(saved.getAccountId());
		return dto;

	}

	@Override
	public AccountDto getAccountById(int accountId) {
		// TODO Auto-generated method stub

		Account acc = accountRepo.findById(accountId).orElse(null);

		if (acc == null) return null;

		AccountDto dto = new AccountDto();

		dto.setAccountId(acc.getAccountId());
		dto.setAccountNumber(acc.getAccountNumber());
		dto.setAccountType(acc.getAccountType());
		dto.setBalance(acc.getBalance());
		dto.setBranchName(acc.getBranchName());

		return dto;

	}

	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub

		List<Account> list = accountRepo.findAll();
		List<AccountDto> result = new java.util.ArrayList<>();

		for (Account acc : list) {

			AccountDto dto = new AccountDto();
			dto.setAccountId(acc.getAccountId());
			dto.setAccountNumber(acc.getAccountNumber());
			dto.setAccountType(acc.getAccountType());
			dto.setBalance(acc.getBalance());
			dto.setBranchName(acc.getBranchName());

			result.add(dto);
		}

		return result;

	}

	@Override
	public AccountDto updateAccount(int accountId, AccountDto dto) {
		// TODO Auto-generated method stub
		Account acc = accountRepo.findById(accountId).orElse(null);

		if (acc == null) return null;

		acc.setAccountType(dto.getAccountType());
		acc.setBalance(dto.getBalance());
		acc.setBranchName(dto.getBranchName());

		accountRepo.save(acc);

		return dto;

	}

	@Override
	public String deleteAccount(int accountId) {
		// TODO Auto-generated method stub

		accountRepo.deleteById(accountId);

		return "Deleted";

	}

	@Override
	public double getBalance(String accountNumber) {
		// TODO Auto-generated method stub

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null) return 0;

		return acc.getBalance();

	}

}
