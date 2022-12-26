package org.example.controller;

import org.example.postgres.model.Person;
import org.example.postgres.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Value("${server.message}")
    private String message;

    @PostMapping
    public ResponseEntity<Person> add(@RequestBody Person person) {
        try {
            Person result = personService.add(person);
            if(result == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Person>> getAll() {
        List<Person> people = personService.getAll();
        if(people.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return message;
    }
}
