package com.example.real_java_spring.controller;

import com.example.real_java_spring.model.Cart;
import com.example.real_java_spring.model.CartItem;
import com.example.real_java_spring.model.Product;
import com.example.real_java_spring.service.CartItemService;
import com.example.real_java_spring.service.CartService;
import com.example.real_java_spring.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final CartItemService cartItemService;




    @Autowired
    public CartController(CartService cartService, ProductService productService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @PostMapping("/{cartId}/add/{productId}")
    public void addItemToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) { // добавляем параметр quantity
        Cart cart = cartService.getCartById(cartId);
        Product product = productService.getProductById(productId);
        cartService.addItemToCart(cart, product, quantity); // передаем quantity
    }

    @PostMapping("/{cartId}/remove/{productId}")
    public void removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) { // добавляем параметр quantity
        Cart cart = cartService.getCartById(cartId);
        Product product = productService.getProductById(productId);
        cartService.removeItemFromCart(cart, product, quantity); // передаем quantity
    }

    @GetMapping("/{cartId}/items")
    public List<CartItem> getCartItems(@PathVariable Long cartId) {
        return cartItemService.getCartItemsByCartId(cartId);
    }

    @PostMapping("/{cartItemId}/updateQuantity")
    public void updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        cartItemService.updateCartItemQuantity(cartItem, quantity);
    }

    @PostMapping("/delete/{cartItemId}")
    public String deleteCartItem(@PathVariable Long cartItemId, HttpServletRequest request) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        Long cartId = cartItem.getCart().getUser().getId();  // we get the id of the cart before deleting the item

        var quanitu = cartItemService.getQuantity(cartItem);
        var productId = cartItem.getProduct().getId();
        productService.increaseProductQuantity(productId,quanitu);

        cartItemService.deleteCartItem(cartItem);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer.substring(0, referer.lastIndexOf('/') + 1) + cartId;
    }


}

