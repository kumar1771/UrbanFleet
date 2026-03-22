package com.example.Auth.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResDto {
    private String accessToken;
    private String role;
    private String name;
    private String userId;
    private String status;
}
