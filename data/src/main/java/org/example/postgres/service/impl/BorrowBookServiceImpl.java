package org.example.postgres.service.impl;

import org.example.dto.BorrowBookDTO;
import org.example.dto.RequestTimer;
import org.example.mysql.model.Book;
import org.example.mysql.service.BookService;
import org.example.postgres.model.BorrowBook;
import org.example.postgres.repository.BorrowBookRepository;
import org.example.postgres.service.BorrowBookService;
import org.example.postgres.service.converter.BorrowBookConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {

    @Autowired
    private BorrowBookRepository borrowBookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowBookConverter borrowBookConverter;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public BorrowBookDTO add(BorrowBookDTO dto) {
        if(dto.getBook_id() != null) {
            Book book = bookService.findById(dto.getBook_id());
            dto.setBorrow_date(dto.getBorrow_date());
            dto.setAuthor_id(book.getAuthor().getId());
            dto.setType_id(book.getBookType().getType_id());
            dto.setFirst_character(Character.toUpperCase(book.getName().charAt(0)));

            BorrowBook borrowBook = borrowBookConverter.convertDTO2Model(dto);
            borrowBook = borrowBookRepository.save(borrowBook);

            return borrowBookConverter.convertModel2DTO(borrowBook);
        }

        return null;
    }

    @Override
    public long countByTimer(RequestTimer timer) {
        return borrowBookRepository.countByTimer(timer.getStart(), timer.getEnd());
    }

    @Override
    public Book getBookByTimer(RequestTimer timer) {
        try {
            long book_id = borrowBookRepository.getBookIdByTimer(timer.getStart(), timer.getEnd());

            return bookService.findById((int) book_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
