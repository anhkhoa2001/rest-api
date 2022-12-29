package org.example.mysql.service.impl;


import org.example.dto.BookDTO;
import org.example.dto.StatisticalByAuthor;
import org.example.dto.StatisticalByCharacter;
import org.example.dto.StatisticalByType;
import org.example.mysql.model.Author;
import org.example.mysql.model.BookType;
import org.example.mysql.model.Book;
import org.example.mysql.repository.AuthorRepository;
import org.example.mysql.repository.BookRepository;
import org.example.mysql.repository.BookTypeRepository;
import org.example.mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Book addBook(BookDTO source) {
        Book target = new Book();
        if(source.getAuthor_id() == null || source.getType_id() == null) {
            return null;
        }

        Author author = authorRepository.findById(source.getAuthor_id()).orElse(null);
        BookType bookType = bookTypeRepository.findById(source.getType_id()).orElse(null);

        if(author != null && bookType != null) {
            target.setAuthor(author);
            target.setBookType(bookType);
            target.setPrice(source.getPrice());
            target.setName(source.getName());
            target.setContent(source.getContent());

            if(source.getId() != null && !source.getId().equals(0)) {
                target.setId(source.getId());
            }

            return bookRepository.save(target);
        }

        return null;
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<StatisticalByAuthor> getAllByAuthor() {
        try {
            String query = "select ra.name as author_name, b.author_id, count(b.name) as total from rest_book b\n" +
                            " inner join rest_author ra on b.author_id = ra.id\n" +
                            " group by b.author_id;";
            List<Object[]> result = entityManager.createNativeQuery(query).getResultList();
            List<StatisticalByAuthor> sbas = new ArrayList<>();
            for(int i=0; i<result.size(); i++) {
                Object[] objects = result.get(i);
                StatisticalByAuthor sba = new StatisticalByAuthor();
                sba.setAuthor_name(objects[0] == null ? null : (objects[0]).toString());
                sba.setAuthor_id(objects[1] == null ? 0 : Integer.parseInt((objects[1]).toString()));
                sba.setTotal(objects[2] == null ? null : Integer.parseInt((objects[2]).toString()));
                sbas.add(sba);
            }
            return sbas;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<StatisticalByType> getAllByType() {
        try {
            String query = "select b.book_type_id, rb.name, count(b.name) as total from rest_book b\n" +
                            " inner join rest_book_type rb on b.book_type_id = rb.type_id\n" +
                            " group by b.book_type_id;";
            List<Object[]> result = entityManager.createNativeQuery(query).getResultList();
            List<StatisticalByType> sbts = new ArrayList<>();
            for(int i=0; i<result.size(); i++) {
                Object[] objects = result.get(i);
                StatisticalByType sbt = new StatisticalByType();
                sbt.setBook_type_id(objects[0] == null ? 0 : Integer.parseInt((objects[0]).toString()));
                sbt.setBook_type_name(objects[1] == null ? null : (objects[1]).toString());
                sbt.setTotal(objects[2] == null ? 0 : Integer.parseInt((objects[2]).toString()));

                sbts.add(sbt);
            }
            return sbts;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<StatisticalByCharacter> getAllByFirstCharacter() {
        List<StatisticalByCharacter> result = new ArrayList<>();
        for(char c = 'A'; c <= 'Z'; ++c){
            String query = "select count(*) from rest_book b where upper(name) like '" + c + "%'";
            Object object = entityManager.createNativeQuery(query)
                                        .getSingleResult();

            StatisticalByCharacter sbc = new StatisticalByCharacter();
            sbc.setBook_character(c);
            sbc.setTotal(object == null ? 0 : Integer.parseInt(object.toString()));

            result.add(sbc);
        }

        return result;
    }
}
