package com.example.demo.model;


import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reviews {
    private int review_seq;
    private int shoe_seq;
    private String review;
    private String  review_rating;
    private String  review_status;
    private Date review_at;
}
