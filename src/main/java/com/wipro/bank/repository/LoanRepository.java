package com.wipro.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bank.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

	  List<Loan> findByCustomerCustomerId(int customerId);
}
