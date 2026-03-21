package com.example.Order.Service.Controller;

import com.example.Order.Service.Dto.OrderRequest;
import com.example.Order.Service.Model.Order;
import com.example.Order.Service.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order placeOrder(
            @RequestHeader("X-User-Id") String buyerId,
            @RequestBody OrderRequest request) {

        return orderService.placeOrder(buyerId, request);
    }

    @GetMapping("/buyer")
    public List<Order> getBuyerOrders(
            @RequestHeader("X-User-Id") String buyerId) {

        return orderService.getBuyerOrders(buyerId);
    }

    @GetMapping("/farmer")
    public List<Order> getFarmerOrders(
            @RequestHeader("X-User-Id") String farmerId) {

        return orderService.getFarmerOrders(farmerId);
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return orderService.updateOrderStatus(id, status);
    }

}