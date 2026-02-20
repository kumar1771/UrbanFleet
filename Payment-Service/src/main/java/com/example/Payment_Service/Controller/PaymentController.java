package com.example.Payment_Service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @GetMapping("/test")
    public String test() {
        return "Payment Service Working!";
    }
}
