package org.example.postgres.service;

import org.example.dto.CustomerDTO;
import org.example.postgres.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    Customer getCustomerByUsername(String username);

    Customer add(CustomerDTO customerDTO);
    Customer getById(Integer id);
}
