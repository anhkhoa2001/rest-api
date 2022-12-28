package org.example.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.Set;

@Data
@RedisHash("BookTypeRD")
public class BookTypeRD {
    private Integer type_id;

    private String name;

    private Set<Integer> book_ids;
}
