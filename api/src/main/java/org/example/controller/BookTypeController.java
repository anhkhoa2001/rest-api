package org.example.controller;

import org.example.cache.model.AuthorRD;
import org.example.cache.model.BookTypeRD;
import org.example.cache.repository.BookTypeRDRepository;
import org.example.mysql.model.Author;
import org.example.mysql.model.BookType;
import org.example.mysql.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book-type")

public class BookTypeController {

    private final BookTypeService bookTypeService;
    private final BookTypeRDRepository bookTypeRDRepository;

    @Autowired
    public BookTypeController(BookTypeService bookTypeService, BookTypeRDRepository bookTypeRDRepository) {
        this.bookTypeService = bookTypeService;
        this.bookTypeRDRepository = bookTypeRDRepository;
    }


    @GetMapping
    public ResponseEntity<List<BookType>> getAll() {
        List<BookType> result = bookTypeService.getAll();
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<BookType> getByName(@RequestParam String name) {
        BookType rs = bookTypeService.getByName(name);
        if(rs == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<BookType> detail(@PathVariable("id") Integer id) {
        if(id == null || id.equals(0)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            bookTypeService.delete(id);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<BookType> addBook(@RequestBody final BookType bookType) {
        BookType rs = bookTypeService.addBookType(bookType);
        if (rs.getType_id() == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<BookType> update(@RequestBody final BookType bookType, @PathVariable("id") Integer id) {
        if(id != null && !id.equals(0)) {
            BookType bookTypeInDB = bookTypeService.findById(id);

            if(bookTypeInDB != null) {
                bookTypeInDB.setName(bookType.getName());
                bookTypeService.addBookType(bookTypeInDB);
                return new ResponseEntity<>(bookTypeInDB, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/cache/{id}")
    public ResponseEntity<BookTypeRD> getBookInCacheById(@PathVariable("id") Integer id) {
        BookTypeRD bookTypeRD = bookTypeRDRepository.findByIdInHash(id);
        if(bookTypeRD != null) {
            return new ResponseEntity<>(bookTypeRD, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/cache")
    public ResponseEntity<List<BookTypeRD>> getAllBookInCache() {
        List<BookTypeRD> bookTypeRDs = bookTypeRDRepository.findAllByHash();
        if(bookTypeRDs == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(bookTypeRDs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bookTypeRDs, HttpStatus.OK);
    }


    @GetMapping(value = "/cache/synchronize")
    public ResponseEntity<List<BookType>> synchronize() {
        List<BookType> bookTypes = bookTypeService.getAll();
        bookTypes = bookTypeRDRepository.saveByHash(bookTypes);
        if(bookTypes == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(bookTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bookTypes, HttpStatus.OK);
    }
}
