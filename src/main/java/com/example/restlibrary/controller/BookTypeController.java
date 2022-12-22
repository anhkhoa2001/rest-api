package com.example.restlibrary.controller;

import com.example.restlibrary.model.BookType;
import com.example.restlibrary.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/book-type")

public class BookTypeController {

    private final BookTypeService bookTypeService;

    @Autowired
    public BookTypeController(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }


    @GetMapping
    public ResponseEntity<List<BookType>> getAll() {
        List<BookType> result = bookTypeService.getAll();
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<BookType> detail(@PathVariable("id") Integer id) {
        BookType bookType = bookTypeService.findById(id);
        if(bookType == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bookType, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        try {
            if(bookTypeService.findById(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            bookTypeService.delete(id);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> addBook(@RequestBody final BookType bookType) {
        try {
            bookTypeService.addBookType(bookType);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<BookType> update(@RequestBody final BookType bookType, @PathVariable("id") Integer id) {
        BookType bookTypeInDB = bookTypeService.findById(id);

        bookTypeInDB.setName(bookType.getName());
        bookTypeService.addBookType(bookTypeInDB);
        return new ResponseEntity<>(bookTypeInDB, HttpStatus.OK);
    }
}
