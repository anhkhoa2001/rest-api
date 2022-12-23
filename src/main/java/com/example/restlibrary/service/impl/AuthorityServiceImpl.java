package com.example.restlibrary.service.impl;

import com.example.restlibrary.model.Authority;
import com.example.restlibrary.repository.AuthorityRepository;
import com.example.restlibrary.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findById(Integer id) {
        return authorityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Authority> findAllById(Set<Integer> authority_ids) {
        return authorityRepository.findAllById(authority_ids);
    }

    @Override
    public Authority add(Authority authority) {
        return authorityRepository.save(authority);
    }
}
