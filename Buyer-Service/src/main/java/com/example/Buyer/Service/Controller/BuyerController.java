package com.example.Buyer.Service.Controller;

import com.example.Buyer.Service.Dto.BuyerRequest;
import com.example.Buyer.Service.Model.Buyer;
import com.example.Buyer.Service.Service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyer")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping("/profile")
    public Buyer createProfile(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody BuyerRequest request){

        return buyerService.createBuyer(userId, request);
    }

    @GetMapping("/profile/me")
    public Buyer getMyProfile(
            @RequestHeader("X-User-Id") String userId){

        return buyerService.getMyProfile(userId);
    }

    @PutMapping("/profile/me")
    public Buyer updateProfile(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody BuyerRequest request){

        return buyerService.updateProfile(userId, request);
    }

    @DeleteMapping("/profile/me")
    public String deleteProfile(
            @RequestHeader("X-User-Id") String userId){

        buyerService.deleteProfile(userId);

        return "Buyer profile deleted successfully";
    }
}
