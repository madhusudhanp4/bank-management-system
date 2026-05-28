package com.wipro.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.bank.dto.LoanDto;
import com.wipro.bank.entity.Loan;
import com.wipro.bank.repository.LoanRepository;

public class LoanServiceImpl  implements ILoanService {


	@Autowired
	private LoanRepository loanRepo;

	@Override
	public LoanDto createLoan(LoanDto dto) {
		// TODO Auto-generated method stub

		Loan loan = new Loan();

		loan.setLoanType(dto.getLoanType());
		loan.setLoanAmount(dto.getLoanAmount());
		loan.setInterestRate(dto.getInterestRate());

		Loan saved = loanRepo.save(loan);

		dto.setLoanId(saved.getLoanId());

		return dto;

	}

	@Override
	public LoanDto getLoanById(int loanId) {
		// TODO Auto-generated method stub

		Loan loan = loanRepo.findById(loanId).orElse(null);

		if (loan == null) return null;

		LoanDto dto = new LoanDto();

		dto.setLoanId(loan.getLoanId());
		dto.setLoanType(loan.getLoanType());
		dto.setLoanAmount(loan.getLoanAmount());
		dto.setInterestRate(loan.getInterestRate());

		return dto;

	}

	@Override
	public List<LoanDto> getAllLoans() {
		// TODO Auto-generated method stub

		List<Loan> list = loanRepo.findAll();
		List<LoanDto> result = new ArrayList<>();

		for (Loan loan : list) {

			LoanDto dto = new LoanDto();

			dto.setLoanId(loan.getLoanId());
			dto.setLoanType(loan.getLoanType());
			dto.setLoanAmount(loan.getLoanAmount());
			dto.setInterestRate(loan.getInterestRate());

			result.add(dto);
		}

		return result;

	}

	@Override
	public List<LoanDto> getLoansByCustomer(int customerId) {
		// TODO Auto-generated method stub

		List<Loan> list = loanRepo.findAll();
		List<LoanDto> result = new java.util.ArrayList<>();

		for (Loan loan : list) {

			LoanDto dto = new LoanDto();

			dto.setLoanId(loan.getLoanId());
			dto.setLoanType(loan.getLoanType());
			dto.setLoanAmount(loan.getLoanAmount());
			dto.setInterestRate(loan.getInterestRate());

			result.add(dto);
		}

		return result;

	}

	@Override
	public LoanDto updateLoan(int loanId, LoanDto dto) {
		// TODO Auto-generated method stub

		Loan loan = loanRepo.findById(loanId).orElse(null);

		if (loan == null) return null;

		loan.setLoanAmount(dto.getLoanAmount());
		loan.setInterestRate(dto.getInterestRate());

		loanRepo.save(loan);

		return dto;

	}

	@Override
	public String deleteLoan(int loanId) {
		// TODO Auto-generated method stub

		loanRepo.deleteById(loanId);

		return "Loan deleted";

	}

}
