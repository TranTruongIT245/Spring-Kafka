package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false, length = 100)
    private String name;
    @Column(name = "email",nullable = false, length = 50)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "balance")
    private double balance;
}
