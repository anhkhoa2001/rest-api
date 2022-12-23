package com.example.restlibrary.service;

import com.example.restlibrary.controller.dto.CustomerDTO;
import com.example.restlibrary.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    Customer getCustomerByUsername(String username);

    Customer add(CustomerDTO customerDTO);
}
