package com.wipro.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
