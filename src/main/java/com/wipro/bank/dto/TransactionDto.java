package com.wipro.bank.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDto {

	private int transactionId;

	private String transactionType;
	private double amount;
	private LocalDate transactionDate;
	
	private String accountNumber;



}
