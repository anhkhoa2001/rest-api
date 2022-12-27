package org.example.controller;

import org.example.dto.RequestTimer;
import org.example.dto.StatisticalByAuthor;
import org.example.dto.StatisticalByCharacter;
import org.example.dto.StatisticalByType;
import org.example.mysql.model.Book;
import org.example.mysql.service.BookService;
import org.example.postgres.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/statistical")
public class StatisticalController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowBookService borrowBookService;

    @GetMapping(value = "/author")
    public ResponseEntity<List<StatisticalByAuthor>> statisAuthor() {
        List<StatisticalByAuthor> result = bookService.getAllByAuthor();
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/character")
    public ResponseEntity<List<StatisticalByCharacter>> statisCharacter() {
        List<StatisticalByCharacter> result = bookService.getAllByFirstCharacter();
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/type")
    public ResponseEntity<List<StatisticalByType>> statisType() {
        List<StatisticalByType> result = bookService.getAllByType();
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/timer")
    public ResponseEntity<Integer> statisticalTimer(@RequestBody RequestTimer timer) {
        try {
            return new ResponseEntity<>((int) borrowBookService.countByTimer(timer), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/most-books")
    public ResponseEntity<Book> statisticalMostBooks(@RequestBody RequestTimer timer) {
        Book book = borrowBookService.getBookByTimer(timer);
        if(book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
