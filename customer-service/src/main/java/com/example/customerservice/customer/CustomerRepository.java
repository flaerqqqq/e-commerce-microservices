package com.example.customerservice.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, Long> {

    boolean existsByEmail(String email);
}
