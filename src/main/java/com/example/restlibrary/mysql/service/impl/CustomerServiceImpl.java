package com.example.restlibrary.mysql.service.impl;

import com.example.restlibrary.mysql.controller.dto.CustomerDTO;
import com.example.restlibrary.mysql.controller.dto.UserDetailCustomize;
import com.example.restlibrary.mysql.model.Customer;
import com.example.restlibrary.mysql.service.CustomerService;
import com.example.restlibrary.mysql.repository.CustomerRepository;
import com.example.restlibrary.mysql.service.converter.CustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public Customer add(CustomerDTO customerDTO) {
        Customer customer = customerConverter.convertDTO2Model(customerDTO);
        if(customer == null) {
            return null;
        }
        customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    public UserDetailCustomize loadUserByUsername(String username) {
        try {
            Customer customer = getCustomerByUsername(username);
            if(customer == null) {
                throw new UsernameNotFoundException(username);
            }
            UserDetailCustomize user = new UserDetailCustomize();
            user.setUsername(customer.getUsername());
            user.setPassword(customer.getPassword());
            user.setAuthorities(customer.getAuthorities());
            user.setStatus(customer.getStatus());

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
