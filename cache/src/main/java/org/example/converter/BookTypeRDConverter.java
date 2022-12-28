package org.example.converter;

import org.example.model.BookTypeRD;
import org.example.mysql.model.BookType;
import org.example.mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookTypeRDConverter {

    @Autowired
    private BookService bookService;

    public BookTypeRD convertModel2RD(BookType source) {
        if(source == null) {
            return null;
        }
        BookTypeRD target = new BookTypeRD();
        target.setType_id(source.getType_id());
        target.setBook_ids(source.getBookIds());
        target.setName(source.getName());

        return target;
    }

    public BookType convertRD2Model(BookTypeRD source) {
        if(source == null) {
            return null;
        }
        BookType target = new BookType();
        target.setType_id(source.getType_id());
        target.setBooks(source.getBook_ids().stream().map(e -> {
            return bookService.findById(e);
        }).collect(Collectors.toSet()));
        target.setName(source.getName());

        return target;
    }
}
