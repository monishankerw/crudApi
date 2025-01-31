package com.crudApi.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long customerId;
    private List<OrderItemRequest> orderItems;
    private PaymentRequest payment;
}