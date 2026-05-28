package com.wipro.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.dto.CustomerDto;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository repo;

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {

        Customer c = new Customer();

        c.setCustomerName(dto.getCustomerName());
        c.setMobile(dto.getMobile());
        c.setEmail(dto.getEmail());
        c.setAddress(dto.getAddress());

    
        c.setStatus("ACTIVE");

        Customer saved = repo.save(c);

        dto.setCustomerId(saved.getCustomerId());

        return dto;
    }

    @Override
    public CustomerDto getCustomerById(int customerId) {

        Customer c = repo.findById(customerId).orElse(null);

        if (c == null || "CLOSED".equals(c.getStatus()))
            return null;

        CustomerDto dto = new CustomerDto();

        dto.setCustomerId(c.getCustomerId());
        dto.setCustomerName(c.getCustomerName());
        dto.setMobile(c.getMobile());
        dto.setEmail(c.getEmail());
        dto.setAddress(c.getAddress());

        return dto;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> list = repo.findAll();

        List<CustomerDto> result = new ArrayList<>();

        for (Customer c : list) {

            // ✅ skip closed customers if needed
            if ("CLOSED".equals(c.getStatus()))
                continue;

            CustomerDto dto = new CustomerDto();

            dto.setCustomerId(c.getCustomerId());
            dto.setCustomerName(c.getCustomerName());
            dto.setMobile(c.getMobile());
            dto.setEmail(c.getEmail());
            dto.setAddress(c.getAddress());

            result.add(dto);
        }

        return result;
    }

    @Override
    public CustomerDto updateCustomer(int customerId, CustomerDto dto) {

        Customer c = repo.findById(customerId).orElse(null);

        if (c == null || "CLOSED".equals(c.getStatus()))
            return null;

        c.setCustomerName(dto.getCustomerName());
        c.setMobile(dto.getMobile());
        c.setEmail(dto.getEmail());
        c.setAddress(dto.getAddress());

        repo.save(c);

        return dto;
    }


    @Override
    public String deleteCustomer(int customerId) {

        Customer c = repo.findById(customerId).orElse(null);

        if (c == null)
            return "Customer not found";

        if ("CLOSED".equals(c.getStatus()))
            return "Customer already closed";

        // ✅ soft delete instead of removing data
        c.setStatus("CLOSED");

        repo.save(c);

        return "Customer account closed successfully ✅";
    }
}