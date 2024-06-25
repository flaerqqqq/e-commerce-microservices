package com.example.customerservice.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Customer {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Address address;
}
