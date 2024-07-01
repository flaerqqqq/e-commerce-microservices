package com.example.orderservice.order;

import com.example.orderservice.orderline.OrderLine;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

    private Long id;

    private String reference;

    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;

    private String customerId;

    private List<OrderLine> orderLines;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedDate;
}
