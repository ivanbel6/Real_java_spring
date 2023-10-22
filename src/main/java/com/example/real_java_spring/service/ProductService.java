package com.example.real_java_spring.service;

import com.example.real_java_spring.model.Product;
import com.example.real_java_spring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public void decreaseProductQuantity(Long id, int quantity){
        Product product = getProductById(id);
        if(product!=null){
            product.setQuantity(product.getQuantity()-quantity);
            productRepository.save(product);
        }
    }

    public void increaseProductQuantity(Long id, int quantity){
        Product product = getProductById(id);
        if(product!=null){
            product.setQuantity(product.getQuantity()+quantity);
            productRepository.save(product);
        }
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}