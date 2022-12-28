package org.example.repository;

import org.example.model.AuthorRD;
import org.example.model.BookTypeRD;
import org.example.mysql.model.Author;
import org.example.mysql.model.BookType;

import java.util.List;

public interface BookTypeRDRepository {

    List<BookType> saveByHash(List<BookType> author);

    List<BookType> saveByList(List<BookType> author);

    List<BookTypeRD> findAllByHash();

    List<BookTypeRD> findAllByList();

    BookTypeRD findByIdInHash(Integer id);
}
