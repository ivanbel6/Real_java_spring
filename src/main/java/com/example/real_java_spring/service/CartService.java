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
    private final ProductService productService; // добавил productService

    public CartService(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService; // инициализация productService в конструкторе
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public void addItemToCart(Cart cart, Product product, int quantity) { // добавляем параметр quantity
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.getCartItems().add(cartItem);
        productService.decreaseProductQuantity(product.getId(), quantity); // decrease product quantity
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Cart cart, Product product, int quantity) { // добавляем параметр quantity
        List<CartItem> cartItems = cart.getCartItems();
        CartItem itemToRemove = cartItems.stream().filter(item -> item.getProduct().equals(product)).findFirst().orElse(null);
        if (itemToRemove != null) {
            productService.increaseProductQuantity(product.getId(), quantity); // increase product quantity
            cartItems.remove(itemToRemove);
            cartRepository.save(cart);
        }
    }
}
