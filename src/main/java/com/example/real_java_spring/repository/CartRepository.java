package com.example.real_java_spring.repository;

import com.example.real_java_spring.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
