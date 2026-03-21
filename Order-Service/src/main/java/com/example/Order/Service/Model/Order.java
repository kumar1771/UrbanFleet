package com.example.Order.Service.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buyerId;

    private String farmerId;

    private Long productId;

    private String productName;

    private Integer quantity;

    private Double pricePerUnit;

    private Double totalPrice;

    private String orderStatus;

    private String deliveryAddress;

    private LocalDateTime orderDate;
}
