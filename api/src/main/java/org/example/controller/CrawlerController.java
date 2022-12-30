package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.crawdata.CrawBookService;
import org.example.crawdata.CrawBookTypeService;
import org.example.dto.RequestCrawBook;
import org.example.mysql.model.Book;
import org.example.mysql.model.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/crawler")
public class CrawlerController {

    @Autowired
    private CrawBookTypeService crawBookTypeService;

    @Autowired
    private CrawBookService crawBookService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<BookType>> crawBookTypes(@RequestParam String url) {
        List<BookType> rs = crawBookTypeService.crawBookTypes(url);
        if(rs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Boolean> crawContentBooks(@RequestBody List<RequestCrawBook> crawBooks) {
        try {
            crawBookService.crawContent(crawBooks);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
