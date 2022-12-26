package org.example.postgres.service.impl;

import org.example.dto.BorrowBookDTO;
import org.example.postgres.service.BorrowBookService;
import org.example.postgres.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {

    @Autowired
    private CustomerService customerService;

    @Override
    public BorrowBookDTO add(BorrowBookDTO dto) {
        return null;
    }

    /*@PersistenceContext
    @Qualifier("postgresEntityManagerFactory")
    private EntityManager entityManager;

    @Override
    @Transactional
    public BorrowBookDTO add(BorrowBookDTO dto) {
        try {
            String query = "insert into pg_borrow_book (borrow_date, exprired_date, " +
                                    "customer_id) values (:borrow_date, :borrow_date, :id)";
            Query q = entityManager.createNativeQuery(query);
            q.setParameter("borrow_date", dto.getBorrow_date());
            q.setParameter("id", dto.getCustomer_id());
            q.executeUpdate();
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
