package com.example.restlibrary.service;

import com.example.restlibrary.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();
    void addBook(Book book);
    Book findById(Integer id);

    void delete(Integer id);

}
