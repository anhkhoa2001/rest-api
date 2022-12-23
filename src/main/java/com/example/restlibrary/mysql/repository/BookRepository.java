package com.example.restlibrary.mysql.repository;

import com.example.restlibrary.mysql.model.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b ORDER BY b.name ASC")
    List<Book> getAllSorted();

    @Query(value="SELECT * FROM rest_book b where b.author_id = :author_id", nativeQuery = true)
    List<Book> getAllByAuthor(@Param("author_id") Integer author_id);

    @Query(value="SELECT * FROM rest_book b where b.book_type_id = :type_id", nativeQuery = true)
    List<Book> getAllByType(@Param("type_id") Integer type_id);

    @Query(value="SELECT * FROM rest_book b where lower(b.name) like :first_c%", nativeQuery = true)
    List<Book> getAllByFirstCharacter(@Param("first_c") Character first_c);
}
