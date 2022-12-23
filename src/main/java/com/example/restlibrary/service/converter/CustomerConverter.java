package com.example.restlibrary.service.converter;

import com.example.restlibrary.controller.dto.CustomerDTO;
import com.example.restlibrary.model.Authority;
import com.example.restlibrary.model.Customer;
import com.example.restlibrary.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class CustomerConverter {

    @Autowired
    private AuthorityService authorityService;

    public Customer convertDTO2Model(CustomerDTO source) {
        Customer target = new Customer();
        if(source.getId() != null && !source.getId().equals(0)) {
            target.setId(source.getId());
        }
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setStatus(source.getStatus());

        if(!source.getAuthority_ids().isEmpty()) {
            List<Authority> authorities = authorityService.findAllById(source.getAuthority_ids());

            target.setAuthorities(new HashSet<>(authorities));

            return target;
        }

        return null;
    }
}
