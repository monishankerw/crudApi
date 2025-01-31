package com.crudApi.controller;

import com.crudApi.constant.Constants;
import com.crudApi.dto.ApiResponse;
import com.crudApi.dto.OrderRequest;
import com.crudApi.entity.Order;

import com.crudApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderRequest request) {
        // Call the service to create the order
        Order order = orderService.createOrder(request);

        // Create ApiResponse and return it with the CREATED status
        ApiResponse response = new ApiResponse(true, Constants.ORDER_CREATED, order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}