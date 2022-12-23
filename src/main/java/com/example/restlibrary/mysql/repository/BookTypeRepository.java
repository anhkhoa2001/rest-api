package com.example.restlibrary.mysql.repository;

import com.example.restlibrary.mysql.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Integer> {
}
