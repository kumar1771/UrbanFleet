package com.example.Auth.service.Service;

import com.example.Auth.service.Dto.LoginReqDto;
import com.example.Auth.service.Dto.LoginResDto;
import com.example.Auth.service.Dto.SignUpDto;
import com.example.Auth.service.EnumForRoles.Role;
import com.example.Auth.service.Model.User;
import com.example.Auth.service.Repositery.UserRepositery;
import com.example.Auth.service.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepositery userRepo;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;
    public String SignUp(SignUpDto signUp) {
        try {
            if (userRepo.existsByEmail(signUp.getEmail())) {
                throw new RuntimeException("Email already exist");
            }
            User user = new User();
            user.setUsername(signUp.getUsername());
            user.setPassword(passwordEncoder.encode(signUp.getPassword()));
            user.setEmail(signUp.getEmail());
            user.setRole(Role.valueOf(signUp.getRole().toUpperCase()));
            userRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Profile created successfully";
    }

    public LoginResDto Login(LoginReqDto login) {
        String uEmail = login.getEmail();
        Optional<User> existingUser = userRepo.findByEmail(login.getEmail());
        String loginPassword = login.getPassword();
        try {
            if(existingUser.isPresent()){
                String existingPassword = existingUser.get().getPassword();
                String existingRole = existingUser.get().getRole().toString();
                if(passwordEncoder.matches(loginPassword,existingPassword)){
                    String token = jwtUtil.generateToken(uEmail, existingRole);
                    LoginResDto loginResDto = new LoginResDto();
                    loginResDto.setAccessToken(token);
                    loginResDto.setStatus("Success");
                    return loginResDto;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
//        if(existingUser.isPresent()){
//            return "Success";
//        }
//        return "NotSuccess";
        return null;
    }
}
