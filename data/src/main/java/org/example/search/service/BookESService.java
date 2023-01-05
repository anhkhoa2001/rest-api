package org.example.search.service;

import org.example.search.model.BookES;
import org.example.mysql.model.Book;

import java.util.List;

public interface BookESService {

    List<BookES> getAll();

    List<BookES> synchronize(List<Book> books);

    BookES findById(Integer id);

    List<BookES> findByName(String name);
}
