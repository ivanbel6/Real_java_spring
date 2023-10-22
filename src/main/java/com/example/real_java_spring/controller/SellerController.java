package com.example.real_java_spring.controller;

import com.example.real_java_spring.model.Product;
import com.example.real_java_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/seller")
public class SellerController {
    private final ProductService productService;

    @Autowired
    public SellerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/seller/add-product";
    }

    @GetMapping("/increase-product-quantity")
    public String getIncreaseProductQuantityPage() {
        return "increase-product-quantity";
    }

    @PostMapping("/increase-product-quantity")
    public String increaseProductQuantity(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity) {
        productService.increaseProductQuantity(productId, quantity);
        return "redirect:/seller/increase-product-quantity";
    }
}
