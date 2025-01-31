package com.crudApi.controller;

import com.crudApi.dto.CustomerRequest;
import com.crudApi.entity.Customer;
import com.crudApi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());

        return customerRepository.save(customer);
    }
}