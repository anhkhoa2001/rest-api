package org.example.search.repository;

import org.example.search.model.BookES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookESRepository extends ElasticsearchRepository<BookES, Integer> {
}
