package org.example.postgres.repository;

import org.example.postgres.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value="select * from pg_customer rc where rc.username= :username", nativeQuery=true)
    Customer findCustomerByUsername(@Param("username") String username);
}
