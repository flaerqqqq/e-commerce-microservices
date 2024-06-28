package com.example.productservice.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequestDto {

    @NotBlank(message = "Product name cannot be null")
    private String name;

    @NotBlank(message = "Product description cannot be null")
    private String description;

    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product quantity cannot be null")
    private BigDecimal price;

    @Positive(message = "Product quantity cannot be null")
    private double availableQuantity;

    @NotNull(message = "Category id cannot be null")
    private Long categoryId;
}
