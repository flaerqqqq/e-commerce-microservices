package com.example.productservice.product;

import com.example.productservice.category.Category;
import com.example.productservice.category.CategoryRepository;
import com.example.productservice.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class)
public abstract class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto toDto(ProductCreateRequestDto request) {
        Category category = categoryRepository.existsById(request.getCategoryId())
                ? categoryRepository.findById(request.getCategoryId()).get()
                : null;
        return ProductDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(category)
                .availableQuantity(request.getAvailableQuantity())
                .build();
    }

    public ProductDto toDto(ProductUpdateRequestDto request) {
        Category category = categoryRepository.existsById(request.getCategoryId())
                ? categoryRepository.findById(request.getCategoryId()).get()
                : null;
        return ProductDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(category)
                .availableQuantity(request.getAvailableQuantity())
                .build();
    }

    public abstract ProductDto toDto(ProductPurchaseRequestDto request);

    public abstract ProductDto toDto(Product product);

    @Mapping(source = "category.id", target = "categoryId")
    public abstract ProductResponseDto toResponseDto(ProductDto productDto);

    public abstract Product toEntity(ProductDto productDto);
}
