package org.example.redis.converter;

import org.example.mysql.model.Author;
import org.example.mysql.service.BookService;
import org.example.redis.model.AuthorRD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorRDConverter {

    @Autowired
    private BookService bookService;

    public AuthorRD convertModel2RD(Author source) {
        if(source == null) {
            return null;
        }
        AuthorRD target = new AuthorRD();
        target.setId(source.getId());
        target.setBook_ids(source.getBookIds());
        target.setName(source.getName());
        target.setAddress(source.getAddress());

        return target;
    }

    public Author convertRD2Model(AuthorRD source) {
        if(source == null) {
            return null;
        }
        Author target = new Author();
        target.setId(source.getId());
        target.setBooks(source.getBook_ids().stream().map(e -> {
            return bookService.findById(e);
        }).collect(Collectors.toSet()));
        target.setName(source.getName());
        target.setAddress(source.getAddress());

        return target;
    }
}
