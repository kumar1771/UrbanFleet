package com.example.Buyer.Service.Service;

import com.example.Buyer.Service.Dto.BuyerRequest;
import com.example.Buyer.Service.Model.Buyer;
import com.example.Buyer.Service.Repositery.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public Buyer createBuyer(String userId, BuyerRequest request){

        Buyer buyer = Buyer.builder()
                .userId(userId)
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .location(request.getLocation())
                .build();

        return buyerRepository.save(buyer);
    }

    public Buyer getMyProfile(String userId){
        return buyerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));
    }

    public Buyer updateProfile(String userId, BuyerRequest request){

        Buyer buyer = buyerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        buyer.setName(request.getName());
        buyer.setEmail(request.getEmail());
        buyer.setPhone(request.getPhone());
        buyer.setLocation(request.getLocation());

        return buyerRepository.save(buyer);
    }

    public void deleteProfile(String userId){

        Buyer buyer = buyerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        buyerRepository.delete(buyer);
    }
}
