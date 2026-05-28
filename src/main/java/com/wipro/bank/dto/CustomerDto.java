package com.wipro.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDto {

	private int customerId;

	private String customerName;
	private String mobile;
	private String email;
	private String address;


}
