package org.example.cache.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;

@Data
@RedisHash("BookTypeRD")
public class BookTypeRD implements Serializable {
    @Id
    private Integer type_id;

    private String name;

    private Set<Integer> book_ids;
}
