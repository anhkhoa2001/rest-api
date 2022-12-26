package org.example.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class BorrowBookDTO {
    private Integer id;
    private Integer customer_id;
    private Date borrow_date;
    private Date expired_date;
    private Set<Integer> book_ids;
}
