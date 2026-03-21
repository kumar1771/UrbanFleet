package com.example.Order.Service.Repository;

import com.example.Order.Service.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByBuyerId(String buyerId);

    List<Order> findByFarmerId(String farmerId);

}
