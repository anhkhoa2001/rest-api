package org.example.cache.repository;

import org.example.mysql.model.Author;

public interface AuthorMCRepository {

    Author findById(Integer id);

    Author save(Author author);

    boolean delete(Integer id);
}
