package com.wipro.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
