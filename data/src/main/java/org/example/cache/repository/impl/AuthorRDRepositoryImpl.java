package org.example.cache.repository.impl;

import org.example.cache.converter.AuthorRDConverter;
import org.example.mysql.model.Author;
import org.example.cache.model.AuthorRD;
import org.example.cache.repository.AuthorRDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AuthorRDRepositoryImpl implements AuthorRDRepository {

    public static final String HASH_KEY = "AuthorRD";

    public static final String LIST_KEY = "AuthorRDs";
    @Autowired
    private RedisTemplate template;

    @Autowired
    private AuthorRDConverter authorRDConverter;


    //hash
    @Override
    public List<Author> saveByHash(List<Author> authors){
        Map<Integer, AuthorRD> map = authors.stream()
                .collect(Collectors.toMap(Author::getId, e -> {
                    return authorRDConverter.convertModel2RD(e);
                }));
        template.opsForHash().putAll(HASH_KEY, map);
        return authors;
    }

    // là 1 linked list có thể lưu các object giống nhau
    //=> khi lấy object sẽ phải yêu cầu index
    //khi xóa cũng phức tạp
    @Override
    public List<Author> saveByList(List<Author> authors) {
        List<AuthorRD> authorRDs = authors.stream().map(e -> {
            return authorRDConverter.convertModel2RD(e);
        }).collect(Collectors.toList());
        template.opsForList().leftPushAll(LIST_KEY,authorRDs);
        return authors;
    }

    //khá giống hash ở chỗ cũng lưu key, value
    //nên lưu những giá trị value đơn giản như url, port .....
    @Override
    public String saveByKV(String key, String value) {
        template.opsForValue().set(key, value);
        return value;
    }

    @Override
    public List<AuthorRD> findAllByHash(){
        return template.opsForHash().values(HASH_KEY);
    }

    @Override
    public List<AuthorRD> findAllByList(){
        return (List<AuthorRD>) template.opsForList().range(LIST_KEY, 0, -1);
    }

    @Override
    public String findByKV(String key) {
        return template.opsForValue().get(key).toString();
    }

    @Override
    public AuthorRD findByIdInHash(Integer id) {
        return (AuthorRD) template.opsForHash().get(HASH_KEY, id);
    }
}
