package com.wipro.bank.mapper;

import com.wipro.bank.dto.TransactionDto;
import com.wipro.bank.entity.Transaction;

public class TransactionMapper {

	//Entity -> DTO (retrieval)
    public static TransactionDto toDto(Transaction txn) {

        TransactionDto dto = new TransactionDto();
        dto.setTransactionType(txn.getTransactionType());
        dto.setAmount(txn.getAmount());
        dto.setAccountNumber(txn.getAccountNumber());
        dto.setStatus(txn.getStatus());

        return dto;
    }
    
    //DTO -> Entity
    public static Transaction toEntity(TransactionDto dto) {
    	
    	Transaction ts = new Transaction();
    	ts.setTransactionType(dto.getTransactionType());
    	ts.setAmount(dto.getAmount());
    	ts.setAccountNumber(dto.getAccountNumber());
    	ts.setStatus(dto.getStatus());
    	
		return ts;
    }
}