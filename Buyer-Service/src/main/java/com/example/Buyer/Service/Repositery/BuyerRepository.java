package com.example.Buyer.Service.Repositery;

import com.example.Buyer.Service.Model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer,Long> {
    Optional<Buyer> findByUserId(String userId);
}
