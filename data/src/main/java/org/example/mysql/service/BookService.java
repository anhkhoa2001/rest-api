package org.example.mysql.service;


import org.example.dto.BookDTO;
import org.example.mysql.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();
    Book addBook(BookDTO book);
    Book findById(Integer id);
    Book update(Book book);
    void delete(Integer id);

}
