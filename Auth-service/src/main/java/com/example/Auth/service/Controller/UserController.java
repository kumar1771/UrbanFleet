package com.example.Auth.service.Controller;

import com.example.Auth.service.Dto.LoginReqDto;
import com.example.Auth.service.Dto.LoginResDto;
import com.example.Auth.service.Dto.SignUpDto;
import com.example.Auth.service.Model.TokenValidationResponse;
import com.example.Auth.service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    public UserService userService;
    @Value("${apikey}")
    private String api_key;
    @PostMapping("signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(userService.SignUp(signUpDto));
    }
    @PostMapping("login")
    public LoginResDto Login(@RequestBody LoginReqDto login){
        return userService.Login(login);
    }
    @GetMapping("/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(
            @RequestHeader("Authorization") String token,
            @RequestHeader("X-Api-key") String apikey) {

        System.out.println("Received Authorization header: " + token);
        System.out.println("Received X-Api-key header: " + apikey);

        if(!apikey.equals(api_key)) {
            System.out.println("API key mismatch");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(token == null || !token.startsWith("Bearer ")) {
            System.out.println("Token missing or invalid format");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        TokenValidationResponse validateToken = userService.validateToken(token);
        System.out.println("Validation result: " + validateToken);

        return ResponseEntity.ok(validateToken);
    }
}
