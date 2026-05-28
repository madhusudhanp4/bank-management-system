package com.wipro.bank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.dto.TransactionDto;
import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.Transaction;
import com.wipro.bank.repository.AccountRepository;
import com.wipro.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements ITransactionService {


	@Autowired
	private TransactionRepository txnRepo;
	

	//Repo to interact with account
	@Autowired
	private AccountRepository accountRepo;


	//Deposit money to account
	@Override
	public String deposit(String accountNumber, double amount) {
		// TODO Auto-generated method stub

		//Find account by account number
		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null) return "Account not found";

		//If account found, increase the balance
		acc.setBalance(acc.getBalance() + amount);
		accountRepo.save(acc);

		
		//Creating the transaction record
		Transaction txn = new Transaction();
		txn.setTransactionType("DEPOSIT");
		txn.setAmount(amount);
		txn.setTransactionDate(LocalDate.now());

		//Link the transaction to account
		txn.setAccount(acc);

		txnRepo.save(txn);

		return "Amount deposited";

	}

	//WITHDRAW MONEY FROM ACCOUNT
	@Override
	public String withdraw(String accountNumber, double amount) {
	

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null) return "Account not found";

		if (acc.getBalance() < amount) {
			return "Insufficient balance";
		}

		// update balance
		acc.setBalance(acc.getBalance() - amount);
		accountRepo.save(acc);

		// save transaction
		Transaction txn = new Transaction();
		txn.setTransactionType("WITHDRAW");
		txn.setAmount(amount);
		txn.setTransactionDate(LocalDate.now());

		txnRepo.save(txn);

		return "Amount withdrawn";

	}

	
	//GET TRANSACTION BY ACCOUNT NUMBER
	@Override
	public List<TransactionDto> getTransactionsByAccount(String accountNumber) {
		// TODO Auto-generated method stub


		List<Transaction> list = txnRepo.findAll();
		List<TransactionDto> result = new ArrayList<>();

		for (Transaction t : list) {

			TransactionDto dto = new TransactionDto();

			dto.setTransactionId(t.getTransactionId());
			dto.setTransactionType(t.getTransactionType());
			dto.setAmount(t.getAmount());
			dto.setTransactionDate(t.getTransactionDate());
			dto.setAccountNumber(accountNumber);

			result.add(dto);
		}

		return result;

	}

}
