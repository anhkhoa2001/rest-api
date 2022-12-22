package com.example.restlibrary.controller;

import com.example.restlibrary.model.Author;
import com.example.restlibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        List<Author> result = authorService.getAll();
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Author> detail(@PathVariable("id") Integer id) {
        Author author = authorService.findById(id);
        if(author == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        try {
            if(authorService.findById(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            authorService.delete(id);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> addBook(@RequestBody final Author author) {
        try {
            authorService.addAuthor(author);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Author> update(@RequestBody final Author author, @PathVariable("id") Integer id) {
        Author authorInDB = authorService.findById(id);

        authorInDB.setAddress(author.getAddress());
        authorInDB.setName(author.getName());
        authorService.addAuthor(authorInDB);
        return new ResponseEntity<>(authorInDB, HttpStatus.OK);
    }
}
