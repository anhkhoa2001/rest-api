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
            log.error("Dữ liệu vào bị lỗi");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dto = borrowBookService.add(dto);
        if(dto == null) {
            log.error("Mượn sách thất bại");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Mượn sách thành công!!!!!!!!!!!");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /*@PostMapping("/generator")
    @Transactional
    public void generator(@RequestParam("start_day") Integer start_day,
                          @RequestParam("start_month") Integer start_month,
                          @RequestParam("end_day") Integer end_day,
                          @RequestParam("year") Integer year) {
        int n = 1;
        final int size = 100000;
        Map<Integer, Book> map = new HashMap<>();
        Random r = new Random();
        int minDay = (int) LocalDate.of(year, start_month, start_day).toEpochDay();
        int maxDay = (int) LocalDate.of(year, start_month, end_day).toEpochDay();
        for(int i=0; i<size; i++) {
            int customer_id = r.nextInt(size) + 1;
            long randomDay = minDay + r.nextInt(maxDay - minDay);

            LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
            Date borrow_date = Date.from(randomBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            int book_id = r.nextInt(13) + 1;
            Book book = map.getOrDefault(book_id, null);
            if(book == null) {
                book = bookService.findById(book_id);
                map.put(book_id, book);
            } else {
                book = map.get(book_id);
            }

            BorrowBook borrowBook = new BorrowBook();
            borrowBook.setBook_id(book_id);
            borrowBook.setBorrow_date(borrow_date);
            borrowBook.setAuthor_id(book.getAuthor().getId());
            borrowBook.setCustomer_id(customer_id);
            borrowBook.setType_id(book.getBookType().getType_id());
            borrowBook.setFirst_character(book.getName().charAt(0));

            borrowBookRepository.save(borrowBook);
        }
    }*/
}
