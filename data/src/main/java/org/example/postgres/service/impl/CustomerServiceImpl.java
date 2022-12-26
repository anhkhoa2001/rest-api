package org.example.postgres.service.impl;

import org.example.dto.CustomerDTO;
import org.example.dto.UserDetailCustomize;
import org.example.postgres.model.Customer;
import org.example.postgres.repository.CustomerRepository;
import org.example.postgres.service.CustomerService;
import org.example.postgres.service.converter.CustomerConverter;
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
    public Customer getById(final Integer id) {
        return customerRepository.findById(id).orElse(null);
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
