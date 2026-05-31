package com.wipro.bank.mapper;

import com.wipro.bank.dto.TransactionDto;
import com.wipro.bank.entity.Transaction;

public class TransactionMapper {

	//Entity -> DTO (retrieval)
    public static TransactionDto toDto(Transaction txn) {

        TransactionDto dto = new TransactionDto();
        dto.setTransactionType(txn.getTransactionType());
        dto.setAmount(txn.getAmount());
        dto.setAccountNumber(txn.getAccount().getAccountNumber());
        dto.setStatus(txn.getStatus());

        return dto;
    }
}