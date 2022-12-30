package org.example.mysql.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name =  "rest_book_type")
public class BookType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer type_id;

    @Column(name = "name", nullable = false, unique = true)
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

    public Set<Integer> getBookIds() {
        /*if(books.isEmpty()) {
            return new HashSet<>();
        }*/
        return books.stream().map(Book::getId).collect(Collectors.toSet());
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
