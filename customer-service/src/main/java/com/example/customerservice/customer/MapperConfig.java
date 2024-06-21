package com.example.customerservice.customer;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueMappingStrategy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public class MapperConfig {
}
