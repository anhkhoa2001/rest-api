package com.example.restlibrary.mysql.controller.dto;

import lombok.Data;

@Data
public class StatisticalByAuthor {

    private String author_name;
    private Integer author_id;
    private Integer total;
}
