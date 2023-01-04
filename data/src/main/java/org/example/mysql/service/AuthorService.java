package org.example.mysql.service;


import org.example.mysql.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author addAuthor(Author book);
    Author findById(Integer id);
    void delete(Integer id);
}
