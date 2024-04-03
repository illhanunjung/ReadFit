package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Comment {
    private int comment_seq;
    private int board_seq;
    private String mem_id;
    private String comment;
    private LocalDateTime comment_at;

    public Comment(int comment_seq, int board_seq, String mem_id, String comment, LocalDateTime comment_at) {
        this.comment_seq = comment_seq;
        this.board_seq = board_seq;
        this.mem_id = mem_id;
        this.comment = comment;
        this.comment_at = comment_at;
    }

        // 기본 생성자
        public Comment() {
        }
}

