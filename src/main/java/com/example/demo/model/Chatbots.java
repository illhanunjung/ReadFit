package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Chatbots {

    private String session_seq;
    private int chat_seq;
    private String mem_id;
    private String contents;
    private LocalDateTime creat_at;
    private String bot_answer;



}
