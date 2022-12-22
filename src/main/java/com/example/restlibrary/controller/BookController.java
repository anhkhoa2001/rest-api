package com.example.restlibrary.controller;

import com.example.restlibrary.model.Book;
import com.example.restlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> result = bookService.getAll();
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Book> detail(@PathVariable("id") Integer id) {
        Book book = bookService.findById(id);
        if(book == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        try {
            bookService.delete(id);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> addBook(@RequestBody final Book book) {
        try {
            bookService.addBook(book);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Book> update(@RequestBody final Book book, @PathVariable("id") Integer id) {
        Book bookInDB = bookService.findById(id);

        bookInDB.setName(book.getName());
        bookInDB.setPrice(book.getPrice());
        bookInDB.setBookType(book.getBookType());
        bookInDB.setAuthor(book.getAuthor());
        bookService.addBook(bookInDB);

        return new ResponseEntity<>(bookInDB, HttpStatus.OK);
    }
}
