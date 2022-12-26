package org.example.postgres.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pg_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;
}
