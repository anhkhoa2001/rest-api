package com.example.restlibrary.mysql.controller;

import com.example.restlibrary.mysql.config.JwtTokenSetup;
import com.example.restlibrary.mysql.controller.dto.CustomerDTO;
import com.example.restlibrary.mysql.model.Authority;
import com.example.restlibrary.mysql.model.Customer;
import com.example.restlibrary.mysql.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenSetup jwt;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Customer> register(@RequestHeader String authorized, @RequestBody CustomerDTO dto) {
        if(authorized != null && authorized.contains("Bearer ")) {
            authorized = authorized.substring(7);
            String username = jwt.getUsernamFromToken(authorized);
            Customer customer = customerService.getCustomerByUsername(username);
            Set<String> authorizations = customer.getAuthorities().stream().map(Authority::getAuthority).collect(Collectors.toSet());
            if(jwt.validateToken(authorized) && authorizations.contains("ADMIN")) {
                dto.setPassword(dto.getPassword() == null ? "2001" : passwordEncoder.encode(dto.getPassword()));
                if(dto.getUsername() == null || customerService.getCustomerByUsername(dto.getUsername()) != null) {
                    LOGGER.error("Tài khoản thêm vào đã bị trùng");
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
                Customer result = customerService.add(dto);
                if(result == null) {
                    LOGGER.error("Thêm mới tài khoản thất bại!");
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                LOGGER.info("Thêm mới tài khoản thành công");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }

            LOGGER.error("Không đủ quyền truy cập");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
