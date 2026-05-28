package com.wipro.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	
	Account findByAccountNumber(String accountNumber);
}
