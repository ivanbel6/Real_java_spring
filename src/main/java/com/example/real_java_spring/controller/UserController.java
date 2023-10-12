package com.example.real_java_spring.controller;

import com.example.real_java_spring.model.Cart;
import com.example.real_java_spring.model.Product;
import com.example.real_java_spring.model.User;
import com.example.real_java_spring.service.CartService;
import com.example.real_java_spring.service.ProductService;
import com.example.real_java_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final ProductService productService; // Теперь автовнедряем ProductService

    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService; // и здесь
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";

    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping("/user-cart/{id}")
    public String getUserCart(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        Cart cart = user.getCart();
        model.addAttribute("cart", cart);
        return "user-cart";
    }
    @GetMapping("/products")
    public String getAllProducts(Model model){
        List<Product> products = productService.getAllProducts(); // теперь ProductService не статический
        model.addAttribute("products", products);
        return "products";
    }

}
