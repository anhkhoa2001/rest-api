package org.example.elasticsearch.service.impl;

import org.example.elasticsearch.model.BookES;
import org.example.elasticsearch.repository.BookESRepository;
import org.example.elasticsearch.service.BookESService;
import org.example.elasticsearch.service.converter.BookESConverter;
import org.example.mysql.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookESServiceImpl implements BookESService {

    @Autowired
    private BookESRepository bookESRepository;

    @Autowired
    private BookESConverter bookESConverter;

    @Override
    public List<BookES> getAll() {
        return (List<BookES>) bookESRepository.findAll();
    }

    @Override
    public List<BookES> synchronize(List<Book> books) {
        try {
            List<BookES> bookESs = books.stream().map(e -> {
                return bookESConverter.convertModel2ES(e);
            }).collect(Collectors.toList());
            bookESs = (List<BookES>) bookESRepository.saveAll(bookESs);
            return bookESs;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public BookES findById(Integer id) {
        return bookESRepository.findById(id).orElse(null);
    }
}
