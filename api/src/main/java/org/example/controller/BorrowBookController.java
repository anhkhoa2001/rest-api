package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.BorrowBookDTO;
import org.example.postgres.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/borrow")
@Slf4j
public class BorrowBookController {

    @Autowired
    private BorrowBookService borrowBookService;


    //2 -> 10
    //7 7 8 9
    //8 8 9 10
    //9 9 10 2
    @PostMapping
    @Transactional
    public ResponseEntity<BorrowBookDTO> borrow(@RequestBody BorrowBookDTO dto) {
        if(dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        dto = borrowBookService.add(dto);

        /*if(dto.getBook_ids() != null && !dto.getBook_ids().isEmpty()) {
            List<CustomerBorrowBook> customerBorrowBooks = new ArrayList<>();
            for(int i:dto.getBook_ids()) {
                CustomerBorrowBook customerBorrowBook = new CustomerBorrowBook();
                customerBorrowBook.setBook_id(i);
                customerBorrowBook.setBorrow_id(dto.getCustomer_id());
                customerBorrowBooks.add(customerBorrowBook);
            }

            customerBorrowBooks = customerBorrowBookService.borrow(customerBorrowBooks);
            if(dto.getBook_ids().size() != customerBorrowBooks.size()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            dto = borrowBookService.add(dto);

            if(dto == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            log.info("Mượn sách thành công");
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }*/

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
