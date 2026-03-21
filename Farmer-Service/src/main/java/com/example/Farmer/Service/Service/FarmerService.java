package com.example.Farmer.Service.Service;


import com.example.Farmer.Service.Dto.FarmerProfileRequest;
import com.example.Farmer.Service.Model.FarmerProfile;
import com.example.Farmer.Service.Repositery.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerProfile createProfile(String userId, FarmerProfileRequest request) {

        if (farmerRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException("Farmer profile already exists");
        }

        FarmerProfile farmer = FarmerProfile.builder()
                .userId(userId)
                .farmName(request.getFarmName())
                .farmType(request.getFarmType())
                .location(request.getLocation())
                .description(request.getDescription())
                .build();

        return farmerRepository.save(farmer);
    }

    public FarmerProfile getMyProfile(String userId) {
        return farmerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public FarmerProfile updateProfile(String userId, FarmerProfileRequest request) {

        FarmerProfile farmer = getMyProfile(userId);

        farmer.setFarmName(request.getFarmName());
        farmer.setFarmType(request.getFarmType());
        farmer.setLocation(request.getLocation());
        farmer.setDescription(request.getDescription());

        return farmerRepository.save(farmer);
    }

    public void deleteProfile(String userId) {
        FarmerProfile farmer = getMyProfile(userId);
        farmerRepository.delete(farmer);
    }
}
