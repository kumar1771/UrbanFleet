package com.example.Farmer.Goods.Service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String farmerId;
//
//    @JsonProperty("name")              // ← maps productName → name
//    private String productName;
//
//    @JsonProperty("price")             // ← maps farmerPrice → price
//    private Double farmerPrice;
//
//    @JsonProperty("stock")             // ← maps quantityAvailable → stock
//    private Integer quantityAvailable;
//
//    @JsonProperty("category")          // ← maps type → category
//    private String type;
//
//    private String unit;
//    private String location;
//    private Double marketPrice;
//    private Date harvestDate;
//    private String farmerId;
}
