package com.example.restlibrary.mysql.service;

import com.example.restlibrary.mysql.controller.dto.BookDTO;
import com.example.restlibrary.mysql.controller.dto.StatisticalByAuthor;
import com.example.restlibrary.mysql.controller.dto.StatisticalByCharacter;
import com.example.restlibrary.mysql.controller.dto.StatisticalByType;
import com.example.restlibrary.mysql.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();
    Book addBook(BookDTO book);
    Book findById(Integer id);
    void delete(Integer id);
    List<StatisticalByAuthor> getAllByAuthor();
    List<StatisticalByType> getAllByType();
    List<StatisticalByCharacter> getAllByFirstCharacter();

}
