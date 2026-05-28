package com.wipro.bank.entity;

import java.time.LocalDate;

import javax.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	
	private String loanType;
	private double loanAmount;
	private double interestRate;
	
	
	
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Loan(int loanId, String loanType, double loanAmount, double interestRate) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
	}




	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	
	
}
