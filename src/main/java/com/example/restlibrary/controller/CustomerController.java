package com.example.restlibrary.controller;

import com.example.restlibrary.config.JwtTokenSetup;
import com.example.restlibrary.controller.dto.CustomerDTO;
import com.example.restlibrary.model.Customer;
import com.example.restlibrary.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenSetup jwt;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Customer> register(@RequestHeader String authorized, @RequestBody CustomerDTO dto) {
        if(jwt.validateToken(authorized)) {
            dto.setPassword(dto.getPassword() == null ? "2001" : passwordEncoder.encode(dto.getPassword()));
            if(dto.getUsername() == null || customerService.getCustomerByUsername(dto.getUsername()) != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            Customer result = customerService.add(dto);
            if(result == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}