package com.crudApi.controller;

import com.crudApi.dto.ApiResponse;
import com.crudApi.dto.CustomerRequest;
import com.crudApi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ApiResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.createCustomer(customerRequest);
        return new ApiResponse<>(true, "Customer created successfully", customer);
    }
}