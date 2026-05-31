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
        List<Loan> activeLoans = loanRepo.findByCustomerCustomerIdAndLoanStatus(dto.getCustomerId(),("ACTIVE"));


        // Business rule: max active loans
        if (activeLoans.size() >= 3)
            return "Loan Rejected: Too many active loans";
        
        double totalLoan = 0;
 
        // Calculate active loans and total amount
        for (Loan loan : activeLoans) {
            totalLoan += loan.getLoanAmount();
        }

        // Business rule: total loan limit
        if (totalLoan + dto.getLoanAmount() > 50000)
            return "Loan limit exceeded";


        // Convert DTO to Entity before saving
        Loan loan = LoanMapper.toEntity(dto, customer);

        loanRepo.save(loan);

        return "Loan Approved ";
    }

    
    // Get complete loan history of customer
    @Override
    public List<LoanDto> getLoanHistory(int customerId) {

        List<Loan> loans = loanRepo.findByCustomerCustomerId(customerId);
        List<LoanDto> dtoList = new ArrayList<>();

        for (Loan loan : loans) {

            // Convert entity to DTO
            dtoList.add(LoanMapper.toDto(loan));
        }

        return dtoList;
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

    
    // Close loan (only after repayments )
    @Override
    public String closeLoan(int loanId) {

        Loan loan = loanRepo.findById(loanId).orElse(null);

        if (loan == null)
            return "Loan not found";

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