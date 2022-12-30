package org.example.mysql.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.mysql.model.BookType;
import org.example.mysql.repository.BookTypeRepository;
import org.example.mysql.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public List<BookType> getAll() {
        return bookTypeRepository.findAll();
    }

    @Override
    public BookType addBookType(BookType bookType) {
        try {
            bookType.setBooks(new HashSet<>());
            return bookTypeRepository.save(bookType);
        } catch (DataIntegrityViolationException e) {
            log.info("Tên tác giả đã bị trùng!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return new BookType();
        }
    }

    @Override
    public BookType findById(Integer id) {
        return bookTypeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        bookTypeRepository.deleteById(id);
    }

    @Override
    public BookType getByName(String name) {
        try {
            return bookTypeRepository.getBookTypeByName(name);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
