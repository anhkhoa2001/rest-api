package com.example.restlibrary.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rest_customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status")
    private Boolean status;
}
