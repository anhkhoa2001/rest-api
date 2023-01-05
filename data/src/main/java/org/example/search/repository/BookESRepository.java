package org.example.search.repository;

import org.example.search.model.BookES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookESRepository extends ElasticsearchRepository<BookES, Integer> {

    List<BookES> findBookESByName(String name);
}
