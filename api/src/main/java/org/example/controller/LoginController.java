package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.LoginRequest;
import org.example.dto.ResponseLogin;
import org.example.dto.UserDetailCustomize;
import org.example.security.jwt.JwtTokenSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenSetup jwt;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<ResponseLogin> login(@RequestBody LoginRequest requestInfo) {
        ResponseLogin response = new ResponseLogin();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestInfo.getUsername(),
                            requestInfo.getPassword()
                    )
            );

            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            response.setMessage(jwt.generateToken((UserDetailCustomize) authentication.getPrincipal()));
            response.setStatus("Đăng nhập thành công");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InternalAuthenticationServiceException e) {
            response.setStatus("Tên tài khoản không tồn tại");
            log.info(e.getMessage());
        } catch (BadCredentialsException e) {
            response.setStatus("Sai mật khẩu");
            log.info(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
