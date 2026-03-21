package com.example.Farmer.Goods.Service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String farmerId;   // From JWT (X-User-Id header)

    private String productName;

    private String category;

    private String type;  // organic / regular

    private Double farmerPrice;

    private Double marketPrice;

    private Integer quantityAvailable;

    private String unit;  // kg, ton, dozen

    private String location;

    private LocalDate harvestDate;

    private Boolean available;
}
