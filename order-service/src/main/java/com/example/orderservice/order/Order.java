package com.example.orderservice.order;

import com.example.orderservice.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "customer_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false, name = "total_amount")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(nullable = false, name = "customer_id")
    private String customerId;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @Column(nullable = false, name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false, name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
}
