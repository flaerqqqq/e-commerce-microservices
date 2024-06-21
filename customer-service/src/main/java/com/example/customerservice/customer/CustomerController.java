package com.example.customerservice.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> register(@RequestBody @Valid RegisterRequestDto request) {
        CustomerDto requestDto = customerMapper.toDto(request);
        CustomerDto responseDto = customerService.register(requestDto);
        return new ResponseEntity<>(customerMapper.toResponse(responseDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> update(@RequestBody @Valid CustomerUpdateRequest request,
                                                      @PathVariable Long id) {
        CustomerDto requestDto = customerMapper.toDto(request);
        CustomerDto responseDto = customerService.update(id, requestDto);
        return new ResponseEntity<>(customerMapper.toResponse(responseDto), HttpStatus.ACCEPTED);
    }
}
