package org.example.postgres.repository;

import org.example.postgres.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Integer> {

    @Query(value = "select count(pg) from pg_borrow_book pg where" +
                " pg.borrow_date >= :start and pg.borrow_date <= :end", nativeQuery = true)
    long countByTimer(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select pp.book_id from (select count(p) as count, p.book_id  " +
            "from pg_borrow_book p where borrow_date >= :start and borrow_date <= :end " +
            "group by book_id) as pp order by pp.count desc limit 1", nativeQuery = true)
    long getBookIdByTimer(@Param("start") Date start, @Param("end") Date end);
}
