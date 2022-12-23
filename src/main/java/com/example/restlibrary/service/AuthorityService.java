package com.example.restlibrary.service;

import com.example.restlibrary.model.Authority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface AuthorityService {

    Authority findById(Integer id);

    List<Authority> findAllById(Set<Integer> authority_ids);

    Authority add(Authority authority);
}
