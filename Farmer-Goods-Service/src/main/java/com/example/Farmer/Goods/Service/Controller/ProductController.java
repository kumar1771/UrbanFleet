package com.example.Farmer.Goods.Service.Controller;

import com.example.Farmer.Goods.Service.Dto.ProductRequest;
import com.example.Farmer.Goods.Service.Service.ProductService;
import com.example.Farmer.Goods.Service.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product addProduct(
            @RequestHeader("X-User-Id") String farmerId,
            @RequestBody ProductRequest request) {

        return productService.addProduct(farmerId, request);
    }

    @GetMapping("/my")
    public List<Product> getMyProducts(
            @RequestHeader("X-User-Id") String farmerId) {

        return productService.getFarmerProducts(farmerId);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @RequestHeader("X-User-Id") String farmerId,
            @PathVariable Long id,
            @RequestBody ProductRequest request) {

        return productService.updateProduct(farmerId, id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return "Product deleted successfully";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProducts(
            @PathVariable Long id) {

        return productService.getProduct(id);
    }
    @PutMapping("/{id}/reduce-stock")
    public void reduceStock(@PathVariable Long id,
                            @RequestParam int quantity) {
        productService.reduceStock(id, quantity);
    }
}
