package com.example.customerservice.customer;


import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CustomerMapper {

    CustomerDto toDto(RegisterRequestDto request);

    CustomerDto toDto(Customer customer);

    RegisterResponseDto toRegisterResponseDto(CustomerDto customerDto);

    Customer fromDto(CustomerDto customerDto);
}

