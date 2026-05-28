package com.wipro.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.dto.LoanDto;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.entity.Loan;
import com.wipro.bank.repository.CustomerRepository;
import com.wipro.bank.repository.LoanRepository;

//  Loan service (bank-controlled operations)
@Service
public class LoanServiceImpl implements ILoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private CustomerRepository customerRepo;

    
    @Override
    public String processLoan(LoanDto dto) {

        Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);

        if (customer == null)
            return "Customer not found";

        // Existing loans
        List<Loan> list = loanRepo.findByCustomerCustomerId(dto.getCustomerId());

        double totalLoan = 0;
        int activeLoans = 0;

        for (Loan loan : list) {
            if ("ACTIVE".equals(loan.getLoanStatus())) {
                totalLoan += loan.getLoanAmount();
                activeLoans++;
            }
        }

        //  Rule 1
        if (totalLoan > 500000)
            return "Loan Rejected: High existing loans";

        //  Rule 2
        if (activeLoans >= 3)
            return "Loan Rejected: Too many active loans";

        //  Create loan (approved)
        Loan loan = new Loan();

        loan.setLoanType(dto.getLoanType());
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setInterestRate(dto.getInterestRate());

        loan.setCustomer(customer);
        loan.setLoanStatus("ACTIVE");

        loanRepo.save(loan);

        return "Loan Approved ";
    }

   
    @Override
    public List<LoanDto> getCustomerLoans(int customerId) {

        List<Loan> list = loanRepo.findByCustomerCustomerId(customerId);

        List<LoanDto> result = new ArrayList<>();

        for (Loan loan : list) {

            LoanDto dto = new LoanDto();

            dto.setLoanId(loan.getLoanId());
            dto.setLoanType(loan.getLoanType());
            dto.setLoanAmount(loan.getLoanAmount());
            dto.setInterestRate(loan.getInterestRate());

            dto.setCustomerId(loan.getCustomer().getCustomerId());

            result.add(dto);
        }

        return result;
    }

   
    @Override
    public double getTotalActiveLoanAmount(int customerId) {

        List<Loan> list = loanRepo.findByCustomerCustomerId(customerId);

        double total = 0;

        for (Loan loan : list) {
            if ("ACTIVE".equals(loan.getLoanStatus())) {
                total += loan.getLoanAmount();
            }
        }

        return total;
    }

    
    @Override
    public String closeLoan(int loanId) {

        Loan loan = loanRepo.findById(loanId).orElse(null);

        if (loan == null)
            return "Loan not found";

        if ("CLOSED".equals(loan.getLoanStatus()))
            return "Loan already closed";

        loan.setLoanStatus("CLOSED");

        loanRepo.save(loan);

        return "Loan closed successfully ✅";
    }

 
    @Override
    public List<LoanDto> getActiveLoans(int customerId) {

        List<Loan> list = loanRepo.findByCustomerCustomerId(customerId);

        List<LoanDto> result = new ArrayList<>();

        for (Loan loan : list) {

            if ("ACTIVE".equals(loan.getLoanStatus())) {

                LoanDto dto = new LoanDto();

                dto.setLoanId(loan.getLoanId());
                dto.setLoanType(loan.getLoanType());
                dto.setLoanAmount(loan.getLoanAmount());
                dto.setInterestRate(loan.getInterestRate());

                dto.setCustomerId(loan.getCustomer().getCustomerId());

                result.add(dto);
            }
        }

        return result;
    }
}
