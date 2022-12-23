package com.example.restlibrary.mysql.controller.dto;

import lombok.Data;

@Data
public class StatisticalByType {

    private Integer book_type_id;
    private String book_type_name;
    private Integer total;
}
