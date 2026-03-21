package com.example.Farmer.Service.Controller;

import com.example.Farmer.Service.Dto.FarmerProfileRequest;
import com.example.Farmer.Service.Model.FarmerProfile;
import com.example.Farmer.Service.Service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmer")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

    @PostMapping("/profile")
    public FarmerProfile create(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody FarmerProfileRequest request) {

        return farmerService.createProfile(userId, request);
    }

    @GetMapping("/profile/me")
    public FarmerProfile getMyProfile(
            @RequestHeader("X-User-Id") String userId) {

        return farmerService.getMyProfile(userId);
    }

    @PutMapping("/profile/me")
    public FarmerProfile update(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody FarmerProfileRequest request) {

        return farmerService.updateProfile(userId, request);
    }

    @DeleteMapping("/profile/me")
    public String delete(
            @RequestHeader("X-User-Id") String userId) {

        farmerService.deleteProfile(userId);
        return "Profile deleted successfully";
    }
}
