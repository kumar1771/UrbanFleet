package com.example.Buyer.Service.Dto;

import lombok.Data;

@Data
public class BuyerRequest {
    private String name;

    private String email;

    private String phone;

    private String location;
}
