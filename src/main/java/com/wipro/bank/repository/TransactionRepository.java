package com.wipro.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {	

	
	/*
	 * Used to get all transactions of a specific account 
	 * Helps show transaction history and track account activity
	 */
	List<Transaction> findByAccount_AccountNumber(String accountNumber);
	
}
