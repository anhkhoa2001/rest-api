package com.example.restlibrary.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rest_book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_type_id", referencedColumnName = "type_id")
    private BookType bookType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
}
