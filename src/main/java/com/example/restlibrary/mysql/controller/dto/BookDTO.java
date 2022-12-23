package com.example.restlibrary.mysql.controller.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Integer id;
    private String name;
    private String price;
    private Integer author_id;
    private Integer type_id;
}
