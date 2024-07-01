package com.example.orderservice.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private double availableQuantity;

    private Long categoryId;
}
