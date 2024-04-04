package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ShoesMapper;
import com.example.demo.model.FavoriteShoesInfo;
import com.example.demo.model.Reviews;
import com.example.demo.model.Shoes;

@Service
public class ShoesService {

    @Autowired
    private ShoesMapper shoesMapper;
 
    public List<Shoes> getShoesByCategorySeq(String parent_category_seq_name) {
        return shoesMapper.getShoesByCategorySeq(parent_category_seq_name);
    }

   // 카테고리별 상위 10개 상품과 그 상품들의 리뷰 정보를 가져오는 메서드
    public List<Shoes> getShoesByCategorySeqtop(String parentCategoryName) {
        List<Shoes> shoesList = shoesMapper.getShoesByCategorySeqtop(parentCategoryName);
        for (Shoes shoes : shoesList) {
            List<Reviews> reviews = shoesMapper.selectReviewsByShoeSeq(shoes.getShoe_seq());
            shoes.setReviews(reviews);
        }
        return shoesList;
    }



    //   public List<Shoes> getShoess() {
    //     return shoesMapper.getShoess();
    // }

    public List<Shoes> getAllShoesWithReviews() {
        return shoesMapper.getAllShoesWithReviews();
    }

    public List<Shoes>  getShoesByCategorySeqs(int category_seq) {
        return shoesMapper. getShoesByCategorySeqs(category_seq);
    }

    public List<Shoes> getTopThreeShoesByCategory(String parentCategoryName) {
        List<Shoes> topThreeShoes = shoesMapper.getTopThreeShoesByCategory(parentCategoryName);
    
   
            for (Shoes shoe : topThreeShoes) {
                List<Reviews> reviews = shoesMapper.selectReviewsByShoeSeq(shoe.getShoe_seq());
                shoe.setReviews(reviews);
            }
        
        return topThreeShoes;
        
        
    }


}

