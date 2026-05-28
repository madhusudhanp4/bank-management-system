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
		// TODO Auto-generated method stub

		Customer c = new Customer();

		c.setCustomerName(dto.getCustomerName());
		c.setMobile(dto.getMobile());
		c.setEmail(dto.getEmail());
		c.setAddress(dto.getAddress());

		Customer saved = repo.save(c);

		dto.setCustomerId(saved.getCustomerId());

		return dto;
	}


	@Override
	public CustomerDto getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		Customer c = repo.findById(customerId).orElse(null);

		if (c == null) return null;

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
		// TODO Auto-generated method stub

		List<Customer> list = repo.findAll();
		List<CustomerDto> result = new ArrayList<>();


		for (Customer c : list) {
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
		// TODO Auto-generated method stub

		Customer c = repo.findById(customerId).orElse(null);

		if (c == null) return null;

		c.setCustomerName(dto.getCustomerName());
		c.setMobile(dto.getMobile());
		c.setEmail(dto.getEmail());
		c.setAddress(dto.getAddress());

		repo.save(c);

		return dto;
	}

	@Override
	public String deleteCustomer(int customerId) {
		// TODO Auto-generated method stub

		repo.deleteById(customerId);
		return "Deleted";

	}




}
