package org.example.cache.repository.impl;

import org.example.cache.repository.AuthorMCRepository;
import org.example.config.cache.MemcacheConfig;
import org.example.mysql.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorMCRepositoryImpl implements AuthorMCRepository {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public Author findById(Integer id) {
        return getCache().get(id, Author.class);
    }

    @Override
    public Author save(Author author) {
        if(author == null) {
            return null;
        }

        getCache().put(author.getId(), author);
        return author;
    }

    @Override
    public boolean delete(Integer id) {
        getCache().evict(id);
        return getCache().get(id, Author.class) == null;
    }

    private Cache getCache() {
        return cacheManager.getCache(MemcacheConfig.CACHE_NAME);
    }
}
