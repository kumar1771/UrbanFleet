package com.example.Farmer.Goods.Service.Repository;

import com.example.Farmer.Goods.Service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByFarmerId(String farmerId);

}