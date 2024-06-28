package com.example.productservice.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseRequestDto {

    @NotNull(message = "Product id cannot be null")
    private Long id;

    @Positive(message = "Quantity should be positive")
    @NotNull(message = "Quantity cannot be null")
    private double quantity;
}
