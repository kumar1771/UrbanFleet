package com.example.Farmer.Service.Repositery;

import com.example.Farmer.Service.Model.FarmerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FarmerRepository extends JpaRepository<FarmerProfile,UUID> {
    Optional<FarmerProfile> findByUserId(String userId);
}
