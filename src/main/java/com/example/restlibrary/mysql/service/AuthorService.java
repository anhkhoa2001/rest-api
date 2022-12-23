package com.example.restlibrary.mysql.service;

import com.example.restlibrary.mysql.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    void addAuthor(Author book);
    Author findById(Integer id);

    void delete(Integer id);
}
