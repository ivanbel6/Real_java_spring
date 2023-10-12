package com.example.real_java_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;

    public List<CartItem> getCartItems() {
        return items;
    }

    public void setCartItems(List<CartItem> items) {
        this.items = items;
    }
}
