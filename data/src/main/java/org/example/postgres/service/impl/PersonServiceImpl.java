package org.example.postgres.service.impl;

import org.example.postgres.model.Person;
import org.example.postgres.repository.PersonRepository;
import org.example.postgres.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }
}
