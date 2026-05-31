package com.wipro.bank.mapper;

import com.wipro.bank.dto.AccountDto;
import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.Customer;

public class AccountMapper {

	//Entity sends data to DTO (for Create or Update DDL operations)
    public static AccountDto toDto(Account acc) {

        AccountDto dto = new AccountDto();
        dto.setAccountType(acc.getAccountType());
        dto.setBalance(acc.getBalance());
        dto.setBranchName(acc.getBranchName());
        dto.setCustomerId(acc.getCustomer().getCustomerId());
        dto.setStatus(acc.getStatus());

        return dto;
    }

    
    //DTO retrieves or gets data from Entity (for GET operations / to display data)
    public static Account toEntity(AccountDto dto, Customer customer) {

        Account acc = new Account();

        acc.setAccountType(dto.getAccountType());
        acc.setBalance(dto.getBalance());
        acc.setBranchName(dto.getBranchName());

        acc.setCustomer(customer);
        acc.setStatus(dto.getStatus());

        return acc;
    }
}
