package com.example.customerservice.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Address address;
}
