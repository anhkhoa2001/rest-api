package com.example.restlibrary.mysql.service.impl;

import com.example.restlibrary.mysql.model.BookType;
import com.example.restlibrary.mysql.service.BookTypeService;
import com.example.restlibrary.mysql.repository.BookTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public List<BookType> getAll() {
        return bookTypeRepository.findAll();
    }

    @Override
    public void addBookType(BookType bookType) {
        bookTypeRepository.save(bookType);
    }

    @Override
    public BookType findById(Integer id) {
        return bookTypeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        bookTypeRepository.deleteById(id);
    }
}
