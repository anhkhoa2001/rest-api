package com.example.restlibrary.service;

import com.example.restlibrary.model.BookType;

import java.util.List;

public interface BookTypeService {

    List<BookType> getAll();
    void addBookType(BookType book);
    BookType findById(Integer id);

    void delete(Integer id);
}
