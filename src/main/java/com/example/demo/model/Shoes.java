package com.example.demo.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias("Shoe")
public class Shoes {

    
    private int shoe_seq;
    private int category_seq;
    private String shoe;
    private int shoe_price;
    private String shoe_img; 
    private String parent_category_seq;
    private String parent_category_seq_name;
    
    // 카테고리 테이블 커럼 category
    private String category;

    // 리뷰총수와 평균별점
    private int reviewCount;
    private double averageRating;
    private List<Reviews> reviews;

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
    
    
}
