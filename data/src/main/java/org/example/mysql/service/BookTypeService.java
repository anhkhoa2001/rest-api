package org.example.mysql.service;


import org.example.mysql.model.BookType;

import java.util.List;

public interface BookTypeService {

    List<BookType> getAll();
    void addBookType(BookType book);
    BookType findById(Integer id);

    void delete(Integer id);
}
