package com.wipro.bank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.bank.dto.TransactionDto;
import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.Transaction;
import com.wipro.bank.repository.AccountRepository;
import com.wipro.bank.repository.TransactionRepository;

public class TransactionServiceImpl implements ITransaction {


	@Autowired
	private AccountRepository accountRepo;


	@Autowired
	private TransactionRepository txnRepo;



	@Override
	public String deposit(String accountNumber, double amount) {
		// TODO Auto-generated method stub

		Account acc = accountRepo.findByAccountNumber(accountNumber);

		if (acc == null) return "Account not found";

		acc.setBalance(acc.getBalance() + amount);
		accountRepo.save(acc);

		Transaction txn = new Transaction();
		txn.setTransactionType("DEPOSIT");
		txn.setAmount(amount);
		txn.setTransactionDate(LocalDate.now());

		txnRepo.save(txn);

		return "Amount deposited";

	}

	@Override
	public String withdraw(String accountNumber, double amount) {
		// TODO Auto-generated method stub

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
