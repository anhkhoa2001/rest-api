package org.example.cache.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;

@Data
@RedisHash("AuthorRD")
public class AuthorRD implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String address;

    private Set<Integer> book_ids;
}
