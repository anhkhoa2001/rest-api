package com.example.restlibrary.mysql.service;

import com.example.restlibrary.mysql.model.BookType;

import java.util.List;

public interface BookTypeService {

    List<BookType> getAll();
    void addBookType(BookType book);
    BookType findById(Integer id);

    void delete(Integer id);
}
