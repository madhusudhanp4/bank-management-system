package com.wipro.bank.service;

import java.util.List;

import com.wipro.bank.dto.CustomerDto;

public interface ICustomerService {

    // Register customer
    String createCustomer(CustomerDto dto);

    // View own profile
    CustomerDto getCustomerById(int customerId);

    // Update own details
    String updateCustomer(int customerId, CustomerDto dto);

    List<CustomerDto> getAllCustomers();
    
    //delete
    String deleteCustomer(int customerId); 
}
