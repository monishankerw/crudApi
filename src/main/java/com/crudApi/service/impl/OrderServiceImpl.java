package com.crudApi.service.impl;

import com.crudApi.dto.OrderItemRequest;
import com.crudApi.dto.OrderRequest;
import com.crudApi.dto.PaymentRequest;
import com.crudApi.entity.*;
import com.crudApi.exception.CustomerNotFoundException;
import com.crudApi.exception.ProductNotFoundException;
import com.crudApi.repository.CustomerRepository;
import com.crudApi.repository.OrderRepository;
import com.crudApi.repository.ProductRepository;
import com.crudApi.repository.PaymentRepository;
import com.crudApi.repository.OrderItemRepository;
import com.crudApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order createOrder(OrderRequest request) {
        logger.info("Creating order for customer ID: {}", request.getCustomerId());

        // Fetch customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        // Create Order entity
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(java.time.LocalDate.now().toString());

        // Process Order Items
        List<OrderItem> orderItems = request.getOrderItems().stream()
                .map(orderItemRequest -> mapOrderItem(orderItemRequest, order))
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);

        // Process Payment
        Payment payment = mapPayment(request.getPayment());
        order.setPayment(payment);

        // Save entities
        orderRepository.save(order);
        paymentRepository.save(payment);
        orderItemRepository.saveAll(orderItems);

        logger.info("Order created successfully with ID: {}", order.getId());
        return order;
    }

    private OrderItem mapOrderItem(OrderItemRequest request, Order order) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());

        return orderItem;
    }

    private Payment mapPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setAmount(request.getAmount());
        return payment;
    }
}