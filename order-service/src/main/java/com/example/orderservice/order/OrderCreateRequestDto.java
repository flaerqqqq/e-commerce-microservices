package com.example.orderservice.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequestDto {

    private Long id;

    private String reference;

    @Positive(message = "Order amount should be positive")
    private BigDecimal amount;

    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Customer should be present")
    private String customerId;

    @NotEmpty(message = "At least 1 product should be in order request")
    private List<PurchaseRequestDto> products;
}
