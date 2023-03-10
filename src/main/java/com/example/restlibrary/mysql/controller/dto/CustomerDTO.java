package com.example.restlibrary.mysql.controller.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CustomerDTO {
    private Integer id;
    private String username;
    private String password;
    private Boolean status;
    private Set<Integer> authority_ids;
}
