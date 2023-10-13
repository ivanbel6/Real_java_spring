package com.example.real_java_spring.service;

import com.example.real_java_spring.model.CartItem;
import com.example.real_java_spring.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getCartItemsByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public void updateCartItemQuantity(CartItem cartItem, int quantity) {
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(Math.toIntExact(cartItemId)).orElse(null);
    }

    public int getQuantity(CartItem cartItem) {
        return cartItem.getQuantity();
    }

    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
}

