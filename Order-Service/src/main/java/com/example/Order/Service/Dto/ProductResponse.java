package com.example.Order.Service.Dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String farmerId;

    private String productName;

    private Double farmerPrice;

    private Integer quantityAvailable;

}
