package com.example.restlibrary.mysql.repository;

import com.example.restlibrary.mysql.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
