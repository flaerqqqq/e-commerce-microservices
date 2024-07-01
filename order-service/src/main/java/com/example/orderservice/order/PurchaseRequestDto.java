package com.example.orderservice.order;

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
public class PurchaseRequestDto {

    @NotNull(message = "Product is mandatory")
    private Long productId;

    @Positive(message = "Product quantity should be positive")
    private double quantity;
}
