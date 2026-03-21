package com.example.Order.Service.Dto;

import lombok.Data;

@Data
public class OrderRequest {

    private Long productId;

    private Integer quantity;

    private String deliveryAddress;

}
