package com.example.Auth.service.Repositery;

import com.example.Auth.service.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositery extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    //User findByEmail(String email);
}
