package org.example.mysql.service;


import org.example.dto.BookDTO;
import org.example.dto.StatisticalByAuthor;
import org.example.dto.StatisticalByCharacter;
import org.example.dto.StatisticalByType;
import org.example.mysql.model.Book;

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