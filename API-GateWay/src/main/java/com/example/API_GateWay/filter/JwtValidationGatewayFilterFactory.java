package com.example.API_GateWay.filter;

import com.example.API_GateWay.Method.TokenValidationResponse;
//import com.example.Api_gateway.Method.TokenValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Component
public class JwtValidationGatewayFilterFactory
        extends AbstractGatewayFilterFactory<JwtValidationGatewayFilterFactory.JwtValidationConfig> {

    @Value("${app.api-key}")
    private String API_KEY;

    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webBuilder) {
        super(JwtValidationConfig.class);
        this.webClient = webBuilder.baseUrl("http://localhost:8081").build(); // Auth Service URL
    }

    @Override
    public GatewayFilter apply(JwtValidationConfig config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            // Check if token is present
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7); // Remove "Bearer " prefix

            // Call Auth Service /validate
            return webClient.get()
                    .uri("/Users/validate")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .header("X-Api-key", API_KEY)
                    .retrieve()
                    .bodyToMono(TokenValidationResponse.class)
                    .flatMap(response -> {
                        if (!response.isValid()) {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }

                        // Role validation (case-insensitive)
                        List<String> allowedRoles = Arrays.asList(config.getAllowedRoles().split(","));
                        boolean roleAllowed = allowedRoles.stream()
                                .anyMatch(r -> r.equalsIgnoreCase(response.getRole()));

                        if (!roleAllowed) {
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return exchange.getResponse().setComplete();
                        }

                        // Token valid & role allowed → continue request
                        return chain.filter(exchange);
                    })
                    .onErrorResume(e -> {
                        // Auth Service down or network error
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

    // Configuration class for allowed roles
    public static class JwtValidationConfig {
        private String allowedRoles;

        public String getAllowedRoles() {
            return allowedRoles;
        }

        public void setAllowedRoles(String allowedRoles) {
            this.allowedRoles = allowedRoles;
        }
    }
}
