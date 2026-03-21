package com.example.Farmer.Goods.Service.Service;

import com.example.Farmer.Goods.Service.Dto.ProductRequest;
import com.example.Farmer.Goods.Service.Repository.ProductRepository;
import com.example.Farmer.Goods.Service.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(String farmerId, ProductRequest request) {

        Product product = Product.builder()
                .farmerId(farmerId)
                .productName(request.getProductName())
                .category(request.getCategory())
                .type(request.getType())
                .farmerPrice(request.getFarmerPrice())
                .marketPrice(request.getMarketPrice())
                .quantityAvailable(request.getQuantityAvailable())
                .unit(request.getUnit())
                .location(request.getLocation())
                .harvestDate(request.getHarvestDate())
                .available(true)
                .build();

        return productRepository.save(product);
    }

    public List<Product> getFarmerProducts(String farmerId) {
        return productRepository.findByFarmerId(farmerId);
    }

    public Product updateProduct(String farmerId, Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getFarmerId().equals(farmerId)) {
            throw new RuntimeException("Unauthorized action");
        }
        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setType(request.getType());
        product.setFarmerPrice(request.getFarmerPrice());
        product.setMarketPrice(request.getMarketPrice());
        product.setQuantityAvailable(request.getQuantityAvailable());
        product.setUnit(request.getUnit());
        product.setLocation(request.getLocation());
        product.setHarvestDate(request.getHarvestDate());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProduct(Long id){
        Product stock = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        return stock;
    }

    public void reduceStock(Long id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        if(product.getQuantityAvailable() < quantity){
            throw new RuntimeException("Not enough stock");
        }
        product.setQuantityAvailable(product.getQuantityAvailable()-quantity);
        productRepository.save(product);
    }
}