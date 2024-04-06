package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Board {
    private int board_seq;
    private String board_title;
    private String board_re_at;
    private String mem_id;
    private String board_content;
    private LocalDateTime board_at;
    private String board_img;

    private String mem_profile;
}
