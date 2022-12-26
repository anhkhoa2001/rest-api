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
    private Date date;

    @Column(name = "customer_id")
    private Integer customer_id;
}
