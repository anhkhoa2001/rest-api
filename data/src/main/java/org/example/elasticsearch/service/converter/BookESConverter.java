package org.example.elasticsearch.service.converter;

import org.example.elasticsearch.model.BookES;
import org.example.mysql.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookESConverter {

    public BookES convertModel2ES(Book source) {
        if(source == null) {
            return null;
        }

        BookES target = new BookES();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setType_id(source.getBookType().getType_id());
        target.setAuthor_id(source.getAuthor().getId());

        return target;
    }
}
