package com.example.orderservice.order;

import com.example.orderservice.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {

    OrderResponseDto toResponseDto(OrderDto orderDto);

    Order toEntity(OrderCreateRequestDto request);
}
