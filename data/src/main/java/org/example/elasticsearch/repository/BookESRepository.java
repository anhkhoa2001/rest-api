package org.example.elasticsearch.repository;

import org.example.elasticsearch.model.BookES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookESRepository extends ElasticsearchRepository<BookES, Integer> {
}
