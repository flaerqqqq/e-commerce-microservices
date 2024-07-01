package com.example.orderservice.order;

import com.example.orderservice.orderline.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;

    private String reference;

    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;

    private String customerId;

    private List<OrderLine> orderLines;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedDate;
}
