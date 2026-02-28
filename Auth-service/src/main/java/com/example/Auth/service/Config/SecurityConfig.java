package com.example.Auth.service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.web.SecurityFilterChain;

@Component
public class SecurityConfig {
    @Bean
    public SecurityFilterChain Filter(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth->auth.anyRequest().permitAll())
                .csrf(csrf-> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(14);
    }
}
