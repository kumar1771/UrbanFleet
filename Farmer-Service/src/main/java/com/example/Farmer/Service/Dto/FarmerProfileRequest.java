package com.example.Farmer.Service.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FarmerProfileRequest {
    @NotBlank
    private String farmName;

    private String farmType;

    private String location;

    private String description;
}
