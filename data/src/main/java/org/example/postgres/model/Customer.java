package org.example.postgres.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pg_customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status")
    private Boolean status;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "customer_authority",
                joinColumns = @JoinColumn(name = "customer_id"),
                inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;
}
