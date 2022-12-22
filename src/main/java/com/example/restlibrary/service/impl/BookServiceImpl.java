package com.example.restlibrary.service.impl;


import com.example.restlibrary.model.Book;
import com.example.restlibrary.repository.BookRepository;
import com.example.restlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllByAuthor(Integer author_id) {
        return bookRepository.getAllByAuthor(author_id);
    }

    @Override
    public List<Book> getAllByType(Integer type) {
        return bookRepository.getAllByType(type);
    }

    @Override
    public List<Book> getAllByFirstCharacter(Character first_c) {
        return bookRepository.getAllByFirstCharacter(first_c);
    }
}
