package org.example.postgres.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "pg_borrow_book")
@Entity
public class BorrowBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "borrow_date")
    private Date borrow_date;

    @Column(name = "customer_id")
    private Integer customer_id;

    @Column(name = "book_id")
    private Integer book_id;

    @Column(name = "author_id")
    private Integer author_id;

    @Column(name = "first_character")
    private Character first_character;

    @Column(name = "type_id")
    private Integer type_id;
}
