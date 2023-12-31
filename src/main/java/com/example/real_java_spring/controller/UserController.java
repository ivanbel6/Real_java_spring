package com.example.real_java_spring.controller;

import com.example.real_java_spring.model.Cart;
import com.example.real_java_spring.model.Product;
import com.example.real_java_spring.model.User;
import com.example.real_java_spring.service.ProductService;
import com.example.real_java_spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    public String getUserCart(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        Cart cart = user.getCart();
        model.addAttribute("cart", cart);
        model.addAttribute("userId", id); // Добавлено для передачи userId обратно при нажатии на "Continue Shopping"
        return "user-cart";
    }
    @GetMapping("/products/{userId}")
    public String getAllProducts(@PathVariable("userId") Long userId, Model model) {
        List<Product> products = productService.getAllProducts();
        User user = userService.findById(userId);
        if (user != null) {
            model.addAttribute("cartId", user.getCart().getId());
        }
        model.addAttribute("products", products);
        return "products";
    }


    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }





}


