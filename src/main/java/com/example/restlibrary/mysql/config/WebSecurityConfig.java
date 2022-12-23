package com.example.restlibrary.mysql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        //inject thuat toan ma hoa vao container
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //xac thuc trong security context
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/authority", "/customer")
                .hasAuthority("ADMIN")
                .antMatchers("/author", "/book-type")
                .hasAnyAuthority("USER")
                .anyRequest().authenticated();

        //xac thuc jwt
        http.addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                        .csrf().disable();
        return http.build();
    }
}
