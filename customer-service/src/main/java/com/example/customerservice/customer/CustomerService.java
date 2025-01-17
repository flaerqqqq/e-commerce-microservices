package com.example.customerservice.customer;

import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.exception.EmailAlreadyUsedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerDto register(CustomerDto customerDto) {
        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new EmailAlreadyUsedException("Email already in use: %s".formatted(customerDto.getEmail()));
        }

        String uuid = UUID.randomUUID().toString();
        customerDto.setId(uuid);

        Customer customer = customerMapper.fromDto(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toDto(savedCustomer);
    }

    public CustomerDto update(String id, CustomerDto customerDto) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with id is not found: %s".formatted(id));
        }

        customerDto.setId(id);
        Customer customer = customerMapper.fromDto(customerDto);

        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.toDto(updatedCustomer);
    }

    public Page<CustomerDto> findAll(Pageable pageable){
        return customerRepository.findAll(pageable)
                .map(customerMapper::toDto);
    }

    public CustomerDto findById(String id){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer with id is not found: %s".formatted(id)));

        return customerMapper.toDto(customer);
    }

    public void delete(String id){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer with id is not found: %s".formatted(id)));

        customerRepository.delete(customer);
    }
}
