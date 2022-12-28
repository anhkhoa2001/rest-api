package org.example.elasticsearch.service;

import org.example.elasticsearch.model.BookES;
import org.example.mysql.model.Book;

import java.util.List;

public interface BookESService {

    List<BookES> getAll();

    List<BookES> synchronize(List<Book> books);

    BookES findById(Integer id);
}
