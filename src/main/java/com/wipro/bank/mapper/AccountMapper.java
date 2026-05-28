package com.wipro.bank.mapper;

import com.wipro.bank.dto.AccountDto;
import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.Customer;

public class AccountMapper {

    public static AccountDto toDto(Account acc) {

        AccountDto dto = new AccountDto();

        dto.setAccountId(acc.getAccountId());
        dto.setAccountNumber(acc.getAccountNumber());
        dto.setAccountType(acc.getAccountType());
        dto.setBalance(acc.getBalance());
        dto.setBranchName(acc.getBranchName());
        dto.setCustomerId(acc.getCustomer().getCustomerId());

        return dto;
    }

    public static Account toEntity(AccountDto dto, Customer customer) {

        Account acc = new Account();

        acc.setAccountNumber(dto.getAccountNumber());
        acc.setAccountType(dto.getAccountType());
        acc.setBalance(dto.getBalance());
        acc.setBranchName(dto.getBranchName());

        acc.setCustomer(customer);
        acc.setStatus("ACTIVE");

        return acc;
    }
}
