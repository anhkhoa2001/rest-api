package org.example.controller;

import org.example.dto.StatisticalByAuthor;
import org.example.dto.StatisticalByCharacter;
import org.example.dto.StatisticalByType;
import org.example.mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/statistical")
public class StatisticalController {

    @Autowired
    private BookService bookService;

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
}
