package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Favorites {
    private int favorite_seq;
    private String mem_id;
    private int shoe_seq;
    private String created_at;

    // Getters and setters
}
