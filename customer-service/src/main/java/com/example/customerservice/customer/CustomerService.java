package com.example.customerservice.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerDto register(CustomerDto customerDto){
        if(customerRepository.existsByEmail(customerDto.getEmail())){
            throw new EmailAlreadyUsedException("Email already in use: %s".formatted(customerDto.getEmail()));
        }

        Customer customer = customerMapper.fromDto(customerDto);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toDto(savedCustomer);
    }
}
