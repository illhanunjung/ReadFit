package com.example.demo.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoriteShoesInfo {
    private int shoe_seq;
    private int category_seq;
    private String shoe;
    private int shoe_price;
    private String shoe_img; 
    private String parent_category_seq;
    private String parent_category_seq_name;
    private String category;
    private int reviewCount;
    private int favoritesCount;
    private double averageRating;

    private List<Reviews> reviews;

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

   

}
