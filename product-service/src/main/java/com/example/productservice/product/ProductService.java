package com.example.productservice.product;

import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.exception.UnavailableProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductDto create(ProductDto productDto){
        Product product = productMapper.toEntity(productDto);

        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public Page<ProductDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    public ProductDto getById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with given id is not found: %d".formatted(id)));
        return productMapper.toDto(product);
    }

    public ProductDto update(Long id, ProductDto productDto) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with given id is not found: %d".formatted(id));
        }

        productDto.setId(id);

        Product product = productMapper.toEntity(productDto);
        Product updatedProduct = productRepository.save(product);

        return productMapper.toDto(updatedProduct);
    }

    public void delete(Long id){
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with given id is not found: %d".formatted(id)));
        productRepository.delete(product);
    }

    public List<ProductDto> purchase(List<ProductPurchaseRequestDto> products) {
        validateProductExistence(products);

        List<Long> productIds = products.stream()
                .map(ProductPurchaseRequestDto::getId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        products.sort(Comparator.comparing(ProductPurchaseRequestDto::getId));
        List<ProductDto> purchaseProducts = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            Product storedProduct = getProduct(products, i, storedProducts);
            productRepository.save(storedProduct);

            purchaseProducts.add(productMapper.toDto(storedProduct));
        }

        return purchaseProducts;
    }

    private Product getProduct(List<ProductPurchaseRequestDto> products, int i, List<Product> storedProducts) {
        ProductPurchaseRequestDto requestProduct = products.get(i);
        Product storedProduct = storedProducts.get(i);

        if (storedProduct.getAvailableQuantity() < requestProduct.getQuantity()){
            throw new UnavailableProductException("Insufficient stock quantity for product with id: %s".formatted(requestProduct.getId()));
        }

        double newAvailableQuantity = storedProduct.getAvailableQuantity() - requestProduct.getQuantity();
        storedProduct.setAvailableQuantity(newAvailableQuantity);
        return storedProduct;
    }

    private void validateProductExistence(List<ProductPurchaseRequestDto> products) {
        List<Long> invalidProductIds = new ArrayList<>();
        for (ProductPurchaseRequestDto product : products) {
            if (!productRepository.existsById(product.getId())){
                invalidProductIds.add(product.getId());
            }
        }
        if (!invalidProductIds.isEmpty()) {
            throw new ProductNotFoundException("Products with id is not found: %s".formatted(invalidProductIds.toString()));
        }
    }
}
