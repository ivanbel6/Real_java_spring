package com.example.real_java_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;


@Data //lam book - getters и setters
@Entity
@Table(name = "users")
public class User {
    @Getter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private int age;

    public void setId(Long id) {
        this.id = id;
    }

}

