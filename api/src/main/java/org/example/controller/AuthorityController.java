package org.example.controller;

import org.example.postgres.model.Authority;
import org.example.postgres.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @PostMapping
    public ResponseEntity<Authority> add(@RequestBody Authority authority) {
        Authority result = authorityService.add(authority);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
