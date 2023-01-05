package org.example.mysql.service.impl;

import org.example.mysql.model.Author;
import org.example.mysql.repository.AuthorRepository;
import org.example.mysql.service.AuthorService;
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
    public Author addAuthor(Author book) {
        try {
            return authorRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author getByName(String name) {
        return authorRepository.getAuthorByName(name);
    }
}
