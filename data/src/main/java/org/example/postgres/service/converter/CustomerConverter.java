package org.example.postgres.service.converter;

import org.example.dto.CustomerDTO;
import org.example.postgres.model.Authority;
import org.example.postgres.model.Customer;
import org.example.postgres.service.AuthorityService;
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

        List<Authority> authorities = authorityService.findAllById(source.getAuthority_ids());
        target.setAuthorities(new HashSet<>(authorities));

        return target;
    }
}
