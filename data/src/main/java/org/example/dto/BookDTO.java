package org.example.dto;


import lombok.Data;

@Data
public class BookDTO {
    private Integer id;
    private String name;
    private String price;
    private String content;
    private String path;
    private Integer author_id;
    private Integer type_id;
}
