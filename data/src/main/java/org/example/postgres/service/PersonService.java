package org.example.postgres.service;


import org.example.postgres.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();
    Person add(Person person);
}
