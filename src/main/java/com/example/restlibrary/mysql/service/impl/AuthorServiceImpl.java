package com.example.restlibrary.mysql.service.impl;

import com.example.restlibrary.mysql.model.Author;
import com.example.restlibrary.mysql.service.AuthorService;
import com.example.restlibrary.mysql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public void addAuthor(Author book) {
        authorRepository.save(book);
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }
}
