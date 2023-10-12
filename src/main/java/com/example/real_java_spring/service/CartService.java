package com.example.real_java_spring.service;

import com.example.real_java_spring.model.Cart;
import com.example.real_java_spring.model.CartItem;
import com.example.real_java_spring.model.Product;
import com.example.real_java_spring.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    public CartService(CartRepository cartRepository, CartItemService cartItemService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public void addItemToCart(Cart cart, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(1); // исходим из предположения, что добавляем 1 продукт
        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Cart cart, Product product) {
        List<CartItem> cartItems = cart.getCartItems();
        CartItem itemToRemove = cartItems.stream().filter(item -> item.getProduct().equals(product)).findFirst().orElse(null);
        if (itemToRemove != null) {
            cartItems.remove(itemToRemove);
            cartRepository.save(cart);
        }
    }


}