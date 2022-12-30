package org.example.mysql.service;


import org.example.mysql.model.BookType;

import java.util.List;

public interface BookTypeService {

    List<BookType> getAll();
    BookType addBookType(BookType book);
    BookType findById(Integer id);
    void delete(Integer id);
    BookType getByName(String name);
}
