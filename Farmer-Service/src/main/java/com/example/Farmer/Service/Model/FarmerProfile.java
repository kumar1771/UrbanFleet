package com.example.Farmer.Service.Model;

//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "farmer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String userId; // From Auth Service (JWT)

    @Column(nullable = false)
    private String farmName;

    private String farmType; // organic, regular, etc.

    private String location;

    @Column(length = 1000)
    private String description;

    private Double rating;

    private Boolean verified;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.verified = false;
        this.rating = 0.0;
    }
}
