package com.example.Order.Service.Feign;

import com.example.Order.Service.Dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service",url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    @PutMapping("/products/{id}/reduce-stock")
    void reduceStock(@PathVariable Long id,
                     @RequestParam int quantity);
}
