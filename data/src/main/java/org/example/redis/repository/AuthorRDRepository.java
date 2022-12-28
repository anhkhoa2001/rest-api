package org.example.redis.repository;

import org.example.mysql.model.Author;
import org.example.redis.model.AuthorRD;

import java.util.List;

public interface AuthorRDRepository {

    List<Author> saveByHash(List<Author> author);

    List<Author> saveByList(List<Author> author);

    String saveByKV(String key, String value);

    List<AuthorRD> findAllByHash();

    List<AuthorRD> findAllByList();

    String findByKV(String key);

    AuthorRD findByIdInHash(Integer id);


}
