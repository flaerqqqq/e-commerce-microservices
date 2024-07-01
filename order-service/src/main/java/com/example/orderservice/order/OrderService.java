package com.example.orderservice.order;

import com.example.orderservice.customer.CustomerClient;
import com.example.orderservice.customer.CustomerResponse;
import com.example.orderservice.exception.CustomerNotFoundException;
import com.example.orderservice.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public OrderDto createOrder(OrderCreateRequestDto request) {
        CustomerResponse customerResponse = customerClient.findCustomerById(request.getCustomerId()).orElseThrow(() ->
                new CustomerNotFoundException("Customer with id is not found: %s".formatted(request.getCustomerId())));

        productClient.purchaseProducts(request.getProducts());

        Order order = orderMapper.toEntity(request);
        Order savedOrder = orderRepository.save(order);
    }
}
