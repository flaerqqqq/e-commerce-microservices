package com.example.productservice.category;

import com.example.productservice.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
