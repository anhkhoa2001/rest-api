package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.CustomerDTO;
import org.example.postgres.model.Customer;
import org.example.postgres.service.AuthorityService;
import org.example.postgres.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;

    @Autowired
    public CustomerController(CustomerService customerService, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Customer> register(@RequestBody CustomerDTO dto) {
        dto.setPassword(dto.getPassword() == null ? "2001" : passwordEncoder.encode(dto.getPassword()));
        if(dto.getUsername() == null || customerService.getCustomerByUsername(dto.getUsername()) != null) {
            log.error("Tài khoản thêm vào đã bị trùng");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if(dto.getAuthority_ids().isEmpty() ||
                authorityService.findAllById(dto.getAuthority_ids()).size() < dto.getAuthority_ids().size()) {
            log.error("Quyền của tài khoản không đúng");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer result = customerService.add(dto);
        if(result == null) {
            log.error("Thêm mới tài khoản thất bại!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("Thêm mới tài khoản thành công");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
