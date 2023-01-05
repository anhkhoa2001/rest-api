package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cache.repository.AuthorMCRepository;
import org.example.mysql.model.Author;
import org.example.mysql.service.AuthorService;
import org.example.cache.model.AuthorRD;
import org.example.cache.repository.AuthorRDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRDRepository authorRDRepository;

    @Autowired
    private AuthorMCRepository authorMCRepository;

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
        if(id == null || id.equals(0)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            authorService.delete(id);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Author> addBook(@RequestBody final Author author) {
        Author rs = authorService.addAuthor(author);
        if(rs == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Author> update(@RequestBody final Author author, @PathVariable("id") Integer id) {
        if(id != null && !id.equals(0)) {
            Author authorInDB = authorService.findById(id);

            if(authorInDB != null) {
                authorInDB.setAddress(author.getAddress());
                authorInDB.setName(author.getName());
                authorService.addAuthor(authorInDB);
                return new ResponseEntity<>(authorInDB, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/cache/{id}")
    public ResponseEntity<AuthorRD> getBookInCacheById(@PathVariable("id") Integer id) {
        AuthorRD author = authorRDRepository.findByIdInHash(id);
        if(author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/cache")
    public ResponseEntity<List<AuthorRD>> getAllBookInCache() {
        List<AuthorRD> authors = authorRDRepository.findAllByHash();
        if(authors == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }


    @GetMapping(value = "/cache/synchronize")
    public ResponseEntity<List<Author>> synchronizeCache() {
        List<Author> authors = authorService.getAll();
        authors = authorRDRepository.saveByHash(authors);
        if(authors == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping(value = "/memcache/synchronize")
    public ResponseEntity<List<Author>> synchronizeMemcache() {
        List<Author> authors = authorService.getAll();
        authors = authors.stream().map(e -> {
            return authorMCRepository.save(e);
        }).collect(Collectors.toList());
        if(authors == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping(value = "/memcache/{id}")
    public ResponseEntity<Author> findByIdInMemcache(@PathVariable Integer id) {
        Author author = authorMCRepository.findById(id);
        if(author == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
