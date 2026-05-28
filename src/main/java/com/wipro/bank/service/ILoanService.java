package com.wipro.bank.service;

import java.util.List;

import com.wipro.bank.dto.LoanDto;

public interface ILoanService {


	LoanDto createLoan(LoanDto dto);

	LoanDto getLoanById(int loanId);

	List<LoanDto> getAllLoans();

	List<LoanDto> getLoansByCustomer(int customerId);

	LoanDto updateLoan(int loanId, LoanDto dto);

	String deleteLoan(int loanId);


}
