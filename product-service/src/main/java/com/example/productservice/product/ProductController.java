package com.example.productservice.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductCreateRequestDto request) {
        ProductDto productDto = productMapper.toDto(request);
        ProductDto responseDto = productService.create(productDto);
        return new ResponseEntity<>(productMapper.toResponseDto(responseDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        Page<ProductResponseDto> products = productService.getAll(pageable).map(productMapper::toResponseDto);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        ProductResponseDto response = productMapper.toResponseDto(productService.getById(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id,
                                                     @RequestBody @Valid ProductUpdateRequestDto request) {
        ProductDto productDto = productMapper.toDto(request);
        ProductResponseDto response = productMapper.toResponseDto(productService.update(id, productDto));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductResponseDto>> purchase(@RequestBody @Valid List<ProductPurchaseRequestDto> request) {
        List<ProductResponseDto> purchaseResponseList = productService.purchase(request)
                .stream()
                .map(productMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(purchaseResponseList);
    }
}
