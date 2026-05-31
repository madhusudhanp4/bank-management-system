package com.wipro.bank.mapper;

import com.wipro.bank.dto.LoanDto;
import com.wipro.bank.entity.Loan;
import com.wipro.bank.entity.Customer;

public class LoanMapper {

	//Entity -> DTO
    public static LoanDto toDto(Loan loan) {

        LoanDto dto = new LoanDto();
        dto.setLoanType(loan.getLoanType());
        dto.setLoanAmount(loan.getLoanAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setLoanStatus(loan.getLoanStatus());
        dto.setCustomerId(loan.getCustomer().getCustomerId());

        return dto;
    }

    //DTO to Entity
    public static Loan toEntity(LoanDto dto, Customer customer) {

        Loan loan = new Loan();

        loan.setLoanType(dto.getLoanType());
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setInterestRate(dto.getInterestRate());
        loan.setLoanStatus("ACTIVE");

        loan.setCustomer(customer);

        return loan;
    }
}
