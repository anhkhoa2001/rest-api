package org.example.postgres.service;

import org.example.dto.BorrowBookDTO;
import org.example.dto.RequestTimer;
import org.example.mysql.model.Book;

public interface BorrowBookService {

    BorrowBookDTO add(BorrowBookDTO dto);

    long countByTimer(RequestTimer timer);

    Book getBookByTimer(RequestTimer timer);
}
