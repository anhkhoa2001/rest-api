package org.example.dto;

import lombok.Data;


@Data
public class CustomerBorrowBookDTO {
    private Integer id;

    private Integer borrow_id;

    private Integer book_id;
}
