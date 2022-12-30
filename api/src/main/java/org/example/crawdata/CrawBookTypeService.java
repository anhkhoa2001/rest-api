package org.example.crawdata;

import org.example.mysql.model.BookType;

import java.util.List;

public interface CrawBookTypeService {

    List<BookType> crawBookTypes(String url);
}
