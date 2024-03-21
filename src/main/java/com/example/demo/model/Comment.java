package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Comment {
    private int comment_seq;
    private int  board_seq;
    private String mem_id;
    private String comment;
    private LocalDateTime comment_at;
}
