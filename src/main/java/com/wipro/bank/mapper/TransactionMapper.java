package com.wipro.bank.mapper;

import com.wipro.bank.dto.TransactionDto;
import com.wipro.bank.entity.Account;
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
    
    //DTO -> Entity
    public static Transaction toEntity(TransactionDto dto) {
    	
    	Account account = new Account();
    	
    	Transaction ts = new Transaction();
    	ts.setTransactionType(dto.getTransactionType());
    	ts.setAmount(dto.getAmount());
    	
    	account.setAccountNumber(dto.getAccountNumber());
    	ts.setAccount(account);
    	
    	ts.setStatus(dto.getStatus());
    	
		return ts;
    }
}