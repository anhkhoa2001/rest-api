package org.example.cache.repository.impl;

import org.example.cache.converter.BookTypeRDConverter;
import org.example.mysql.model.BookType;
import org.example.cache.model.BookTypeRD;
import org.example.cache.repository.BookTypeRDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BookTypeRDRepositoryImpl implements BookTypeRDRepository {
    public static final String HASH_KEY = "BookTypeRD";

    public static final String LIST_KEY = "BookTypeRDs";
    @Autowired
    private RedisTemplate template;

    @Autowired
    private BookTypeRDConverter bookTypeRDConverter;
    @Override
    public List<BookType> saveByHash(List<BookType> bookTypes) {
        Map<Integer, BookTypeRD> map = bookTypes.stream()
                .collect(Collectors.toMap(BookType::getType_id, e -> {
                    return bookTypeRDConverter.convertModel2RD(e);
                }));
        template.opsForHash().putAll(HASH_KEY, map);
        return bookTypes;
    }

    @Override
    public List<BookType> saveByList(List<BookType> bookTypes) {
        List<BookTypeRD> bookTypeRDs = bookTypes.stream().map(e -> {
            return bookTypeRDConverter.convertModel2RD(e);
        }).collect(Collectors.toList());
        template.opsForList().leftPushAll(LIST_KEY, bookTypeRDs);
        return bookTypes;
    }

    @Override
    public List<BookTypeRD> findAllByHash() {
        return template.opsForHash().values(HASH_KEY);
    }

    @Override
    public List<BookTypeRD> findAllByList() {
        return (List<BookTypeRD>) template.opsForList().range(LIST_KEY, 0, -1);
    }

    @Override
    public BookTypeRD findByIdInHash(Integer id) {
        return (BookTypeRD) template.opsForHash().get(HASH_KEY, id);
    }
}
