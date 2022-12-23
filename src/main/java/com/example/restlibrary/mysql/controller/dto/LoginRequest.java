package com.example.restlibrary.mysql.controller.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
