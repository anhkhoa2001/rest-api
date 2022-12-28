package org.example.redis.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.Set;

@Data
@RedisHash("BookTypeRD")
public class BookTypeRD {
    @Id
    private Integer type_id;

    private String name;

    private Set<Integer> book_ids;
}
