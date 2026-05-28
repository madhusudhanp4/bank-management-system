package com.wipro.bank.service;

import java.util.List;

import com.wipro.bank.dto.CustomerDto;

public interface ICustomerService {
	
	
	CustomerDto createCustomer(CustomerDto dto);
	
	CustomerDto getCustomerById(int customerId);
	
	List<CustomerDto> getAllCustomers();
	
	CustomerDto updateCustomer(int customerId, CustomerDto dto);
	
	String deleteCustomer(int customerId);

}
