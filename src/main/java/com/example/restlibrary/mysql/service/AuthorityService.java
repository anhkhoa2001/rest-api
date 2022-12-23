package com.example.restlibrary.mysql.service;

import com.example.restlibrary.mysql.model.Authority;

import java.util.List;
import java.util.Set;

public interface AuthorityService {

    Authority findById(Integer id);

    List<Authority> findAllById(Set<Integer> authority_ids);

    Authority add(Authority authority);
}
