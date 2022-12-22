package com.example.restlibrary.service.impl;

import com.example.restlibrary.model.Author;
import com.example.restlibrary.repository.AuthorRepository;
import com.example.restlibrary.service.AuthorService;
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
