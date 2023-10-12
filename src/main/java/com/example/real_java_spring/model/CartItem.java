package com.example.real_java_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "cart_item")
public class CartItem {
    @Getter
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public void setId(Long id) {
        this.id = id;
    }
}
