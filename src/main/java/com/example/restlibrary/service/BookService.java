package com.example.restlibrary.service;

import com.example.restlibrary.controller.dto.BookDTO;
import com.example.restlibrary.controller.dto.StatisticalByAuthor;
import com.example.restlibrary.controller.dto.StatisticalByCharacter;
import com.example.restlibrary.controller.dto.StatisticalByType;
import com.example.restlibrary.model.Book;

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
