package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Categories {
    private int category_seq;
    private String parent_category_seq;
    private String category;
    private int category_level;
    private String parent_category_seq_name;


}
