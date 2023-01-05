package org.example.mysql.service;


import org.example.mysql.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author addAuthor(Author author);
    Author findById(Integer id);
    void delete(Integer id);

    Author getByName(String name);
}
