package com.crudApi.service;

import com.crudApi.dto.OrderItemRequest;
import com.crudApi.dto.OrderRequest;
import com.crudApi.entity.Order;
import com.crudApi.entity.OrderItem;

public interface OrderService {
    public Order createOrder(OrderRequest request);

}
