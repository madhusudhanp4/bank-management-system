package com.wipro.bank.entity;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;

	private String accountNumber;
	private String accountType;
	private double balance;
	private String branchName;
	

	private String status;
	
	//Many accounts belong to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    //One account can have multiple transactions
	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;



}
