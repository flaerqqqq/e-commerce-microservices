package com.example.productservice.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private double availableQuantity;

    private Long categoryId;
}
