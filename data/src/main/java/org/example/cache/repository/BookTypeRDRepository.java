package org.example.cache.repository;

import org.example.mysql.model.BookType;
import org.example.cache.model.BookTypeRD;

import java.util.List;

public interface BookTypeRDRepository {

    List<BookType> saveByHash(List<BookType> author);

    List<BookType> saveByList(List<BookType> author);

    List<BookTypeRD> findAllByHash();

    List<BookTypeRD> findAllByList();

    BookTypeRD findByIdInHash(Integer id);
}
