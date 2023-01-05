package org.example.controller;

import org.example.dto.BookDTO;
import org.example.search.model.BookES;
import org.example.search.repository.BookESRepository;
import org.example.search.service.BookESService;
import org.example.mysql.model.Book;
import org.example.mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookESService bookESService;
    @Autowired
    private BookESRepository bookESRepository;

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
        if(id == null || id.equals(0)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book book = bookService.findById(id);
        if(book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        try {
            if(bookService.findById(id) == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            bookService.delete(id);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Book> addBook(@RequestBody final BookDTO dto) {
        try {
            Book result = bookService.addBook(dto);

            if(result == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Book> update(@RequestBody final BookDTO dto, @PathVariable("id") Integer id) {
        try {
            dto.setId(id);
            Book result = bookService.addBook(dto);

            if(result == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(value = "/elastic")
    public ResponseEntity<List<BookES>> getAllInElastic() {
        List<BookES> bookESs = bookESService.getAll();

        if(bookESs == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(bookESs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bookESs, HttpStatus.OK);
    }

    @GetMapping(value = "/elastic/synchronize")
    public ResponseEntity<List<BookES>> synchronize() {
        List<Book> books = bookService.getAll();
        List<BookES> bookESs = bookESService.synchronize(books);

        if(bookESs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bookESs, HttpStatus.OK);
    }

    @GetMapping(value = "/elastic/{id}")
    public ResponseEntity<BookES> findByIdInElastic(@PathVariable Integer id) {
        BookES bookES = bookESService.findById(id);

        if(bookES == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bookES, HttpStatus.OK);
    }

    @GetMapping(value = "/elastic/getbyname")
    public ResponseEntity<List<BookES>> findByNameInElastic(@Param("name") String name) {
        List<BookES> bookES = bookESService.findByName(name);

        if(bookES == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(bookES.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bookES, HttpStatus.OK);
    }

}
