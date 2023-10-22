package com.example.real_java_spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data //lombok - getters и setters
@Entity
@Table(name = "users")
public class User {
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    private String password;
}
