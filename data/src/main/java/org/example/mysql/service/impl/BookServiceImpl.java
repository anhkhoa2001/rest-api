package org.example.mysql.service.impl;


import org.example.dto.BookDTO;
import org.example.mysql.model.Author;
import org.example.mysql.model.Book;
import org.example.mysql.model.BookType;
import org.example.mysql.repository.AuthorRepository;
import org.example.mysql.repository.BookRepository;
import org.example.mysql.repository.BookTypeRepository;
import org.example.mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Book addBook(BookDTO source) {
        Book target = new Book();
        if(source.getAuthor_id() == null || source.getType_id() == null) {
            return null;
        }

        Author author = authorRepository.findById(source.getAuthor_id()).orElse(null);
        BookType bookType = bookTypeRepository.findById(source.getType_id()).orElse(null);

        if(author != null && bookType != null) {
            target.setAuthor(author);
            target.setBookType(bookType);
            target.setPrice(source.getPrice());
            target.setName(source.getName());
            target.setContent(source.getContent());

            if(source.getId() != null && !source.getId().equals(0)) {
                target.setId(source.getId());
            }

            return bookRepository.save(target);
        }

        return null;
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

}
