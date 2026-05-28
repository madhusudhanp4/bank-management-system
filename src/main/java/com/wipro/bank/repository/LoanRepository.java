package com.wipro.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bank.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

}
