package org.example.postgres.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "pg_authority")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "authority", nullable = false, unique = true)
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority() {

    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
