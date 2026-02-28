package com.example.Auth.service.Controller;

import com.example.Auth.service.Dto.LoginReqDto;
import com.example.Auth.service.Dto.LoginResDto;
import com.example.Auth.service.Dto.SignUpDto;
import com.example.Auth.service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    public UserService userService;
    @PostMapping("signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(userService.SignUp(signUpDto));
    }
    @PostMapping("login")
    public LoginResDto Login(@RequestBody LoginReqDto login){
        return userService.Login(login);
    }
}
