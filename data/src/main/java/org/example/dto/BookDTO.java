package org.example.dto;


import lombok.Data;

@Data
public class BookDTO {

    private Integer id;
    private String name;
    private String content;
    private Integer author_id;
    private Integer type_id;
}
