package com.wipro.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.dto.AccountDto;
import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.repository.AccountRepository;
import com.wipro.bank.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private CustomerRepository customerRepo;

  
    @Override
    public AccountDto createAccount(AccountDto dto) {

        // fetch customer
        Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);

        if (customer == null) return null;

        Account acc = new Account();

        acc.setAccountNumber(dto.getAccountNumber());
        acc.setAccountType(dto.getAccountType());
        acc.setBalance(dto.getBalance());
        acc.setBranchName(dto.getBranchName());

        acc.setCustomer(customer);
        acc.setStatus("ACTIVE"); //  optional field

        Account saved = accountRepo.save(acc);

        dto.setAccountId(saved.getAccountId());

        return dto;
    }


    @Override
    public AccountDto getAccountByNumber(String accountNumber) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null || "CLOSED".equals(acc.getStatus()))
            return null;

        AccountDto dto = new AccountDto();

        dto.setAccountId(acc.getAccountId());
        dto.setAccountNumber(acc.getAccountNumber());
        dto.setAccountType(acc.getAccountType());
        dto.setBalance(acc.getBalance());
        dto.setBranchName(acc.getBranchName());

        dto.setCustomerId(acc.getCustomer().getCustomerId());

        return dto;
    }

 
    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> list = accountRepo.findAll();
        List<AccountDto> result = new ArrayList<>();

        for (Account acc : list) {

            if ("CLOSED".equals(acc.getStatus()))
                continue;

            AccountDto dto = new AccountDto();

            dto.setAccountId(acc.getAccountId());
            dto.setAccountNumber(acc.getAccountNumber());
            dto.setAccountType(acc.getAccountType());
            dto.setBalance(acc.getBalance());
            dto.setBranchName(acc.getBranchName());

            dto.setCustomerId(acc.getCustomer().getCustomerId());

            result.add(dto);
        }

        return result;
    }

    @Override
    public double getBalancebyNumber(String accountNumber) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null) return 0;

        return acc.getBalance();
    }


    @Override
    public AccountDto updateAccountDetails(String accountNumber, AccountDto dto) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null || "CLOSED".equals(acc.getStatus()))
            return null;

        acc.setAccountType(dto.getAccountType());
        acc.setBalance(dto.getBalance());
        acc.setBranchName(dto.getBranchName());

        accountRepo.save(acc);

        return dto;
    }


    @Override
    public String closeAccount(String accountNumber) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null)
            return "Account not found";

        if ("CLOSED".equals(acc.getStatus()))
            return "Already closed";

        acc.setStatus("CLOSED");

        accountRepo.save(acc);

        return "Account closed successfully ✅";
    }
}
