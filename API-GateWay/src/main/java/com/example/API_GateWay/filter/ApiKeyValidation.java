package com.example.API_GateWay.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyValidation extends AbstractGatewayFilterFactory<Object> {
    @Value("${app.api-key}")
    private String API_KEY;

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            String apikey = exchange.getRequest().getHeaders().getFirst("X-Api-Key");
            if(apikey==null||!apikey.equals(API_KEY)){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        });
    }
}
