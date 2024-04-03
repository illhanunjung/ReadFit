package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Keywords {
    
    private int keyword_seq;
    private int review_seq;
    private String keyword_name;
    private String keyword_text;
    private String start_idx;
    private String end_idx;
    private int review_status;
    private int keyword_polarity;

}
