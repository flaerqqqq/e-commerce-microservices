package com.example.customerservice.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto request){
        CustomerDto requestDto = customerMapper.toDto(request);
        CustomerDto responseDto = customerService.register(requestDto);
        return new ResponseEntity<>(customerMapper.toRegisterResponseDto(requestDto), HttpStatus.CREATED);
    }
}
