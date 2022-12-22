package com.example.restlibrary.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name =  "rest_book_type")
public class BookType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer type_id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "bookType")
    private Set<Book> books;

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
