package com.wipro.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.dto.LoanDto;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.entity.Loan;
import com.wipro.bank.mapper.LoanMapper;
import com.wipro.bank.repository.CustomerRepository;
import com.wipro.bank.repository.LoanRepository;

@Service
public class LoanServiceImpl implements ILoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private CustomerRepository customerRepo;

    
    // Apply loan request based on customer eligibility
    @Override
    public String applyLoan(LoanDto dto) {

        // Check if customer exists
        Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);

        if (customer == null)
            return "Customer not found";

        // Fetch all loans of the customer
        List<Loan> list = loanRepo.findByCustomerCustomerId(dto.getCustomerId());

        double totalLoan = 0;
        int activeLoans = 0;

        // Calculate active loans and total amount
        for (Loan loan : list) {
            if ("ACTIVE".equals(loan.getLoanStatus())) {
                totalLoan += loan.getLoanAmount();
                activeLoans++;
            }
        }

        // Business rule: total loan limit
        if (totalLoan > 500000)
            return "Loan Rejected: High existing loans";

        // Business rule: max active loans
        if (activeLoans >= 3)
            return "Loan Rejected: Too many active loans";

        // Convert DTO to Entity before saving
        Loan loan = LoanMapper.toEntity(dto, customer);

        loanRepo.save(loan);

        return "Loan Approved ";
    }

    
    // Get complete loan history of customer
    @Override
    public List<LoanDto> getLoanHistory(int customerId) {

        List<Loan> list = loanRepo.findByCustomerCustomerId(customerId);
        List<LoanDto> result = new ArrayList<>();

        for (Loan loan : list) {

            // Convert entity to DTO
            result.add(LoanMapper.toDto(loan));
        }

        return result;
    }

    
    // Get total outstanding active loan amount
    @Override
    public double getTotalOutstandingLoan(int customerId) {

        List<Loan> list = loanRepo.findByCustomerCustomerId(customerId);

        double total = 0;

        for (Loan loan : list) {

            if ("ACTIVE".equals(loan.getLoanStatus())) {
                total += loan.getLoanAmount();
            }
        }

        return total;
    }

    
    // Close loan (only after repayment - logic can be enhanced later)
    @Override
    public String closeLoan(int loanId) {

        Loan loan = loanRepo.findById(loanId).orElse(null);

        if (loan == null)
            return "Loan not found";

        if ("CLOSED".equals(loan.getLoanStatus()))
            return "Loan already closed";

        // Updating existing entity status (not creating new one)
        loan.setLoanStatus("CLOSED");

        loanRepo.save(loan);

        return "Loan closed successfully ";
    }

    
    // Get only active loans of customer
    @Override
    public List<LoanDto> getActiveLoans(int customerId) {

        List<Loan> list = loanRepo.findByCustomerCustomerId(customerId);
        List<LoanDto> result = new ArrayList<>();

        for (Loan loan : list) {

            if ("ACTIVE".equals(loan.getLoanStatus())) {

                // Convert active entity to DTO
                result.add(LoanMapper.toDto(loan));
            }
        }

        return result;
    }
}