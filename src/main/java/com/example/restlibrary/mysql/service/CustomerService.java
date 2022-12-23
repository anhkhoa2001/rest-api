package com.example.restlibrary.mysql.service;

import com.example.restlibrary.mysql.controller.dto.CustomerDTO;
import com.example.restlibrary.mysql.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    Customer getCustomerByUsername(String username);

    Customer add(CustomerDTO customerDTO);
}
