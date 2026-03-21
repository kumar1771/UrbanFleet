package com.example.Order.Service.Service;

import com.example.Order.Service.Dto.OrderRequest;
import com.example.Order.Service.Dto.ProductResponse;
import com.example.Order.Service.Feign.ProductClient;
import com.example.Order.Service.Model.Order;
import com.example.Order.Service.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public Order placeOrder(String buyerId, OrderRequest request) {

        ProductResponse product = productClient.getProduct(request.getProductId());

        if (request.getQuantity() > product.getQuantityAvailable()) {
            throw new RuntimeException("Not enough stock");
        }

        double totalPrice = product.getFarmerPrice() * request.getQuantity();
        productClient.reduceStock(request.getProductId(), request.getQuantity());
        Order order = Order.builder()
                .buyerId(buyerId)
                .farmerId(product.getFarmerId())
                .productId(product.getId())
                .productName(product.getProductName())
                .quantity(request.getQuantity())
                .pricePerUnit(product.getFarmerPrice())
                .totalPrice(totalPrice)
                .orderStatus("PLACED")
                .deliveryAddress(request.getDeliveryAddress())
                .orderDate(LocalDateTime.now())
                .build();
//        productClient.reduceStock(request.getProductId(), request.getQuantity());
        return orderRepository.save(order);
    }

    public List<Order> getBuyerOrders(String buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    public List<Order> getFarmerOrders(String farmerId) {
        return orderRepository.findByFarmerId(farmerId);
    }

    public Order updateOrderStatus(Long id, String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(status);

        return orderRepository.save(order);
    }

}
