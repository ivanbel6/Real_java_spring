package com.example.real_java_spring.service;

import com.example.real_java_spring.model.Product;
import com.example.real_java_spring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private static ProductRepository productRepository = null;

    public ProductService(ProductRepository productRepository) {
        ProductService.productRepository = productRepository;
    }

    public static List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(Math.toIntExact(id)).orElse(null);
    }


}
