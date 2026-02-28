package com.example.API_GateWay.Method;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenValidationResponse {
    private boolean valid;
    private String username;
    private String role;
}
