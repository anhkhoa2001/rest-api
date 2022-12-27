package org.example.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.Set;

@Data
public class BorrowBookDTO {
    private Integer id;
    private Integer customer_id;
    private Date borrow_date;
    private Integer book_id;
    private Integer author_id;
    private Character first_character;
    private Integer type_id;
}
