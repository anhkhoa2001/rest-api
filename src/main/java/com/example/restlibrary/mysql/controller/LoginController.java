package com.example.restlibrary.mysql.controller;

import com.example.restlibrary.mysql.config.JwtTokenSetup;
import com.example.restlibrary.mysql.controller.dto.LoginRequest;
import com.example.restlibrary.mysql.controller.dto.UserDetailCustomize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenSetup jwt;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest requestInfo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestInfo.getUsername(),
                        requestInfo.getPassword()
                )
        );

        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwt.generateToken((UserDetailCustomize) authentication.getPrincipal());
    }
}
