package com.example.Farmer.Goods.Service.Dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ProductRequest {
    private String productName;

    private String category;

    private String type;

    private Double farmerPrice;

    private Double marketPrice;

    private Integer quantityAvailable;

    private String unit;

    private String location;

    private LocalDate harvestDate;
}
