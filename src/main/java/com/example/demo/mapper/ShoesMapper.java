package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.FavoriteShoesInfo;
import com.example.demo.model.KeywordCount;
import com.example.demo.model.Reviews;
import com.example.demo.model.Shoes;

@Mapper
public interface ShoesMapper {
    List<Shoes> getShoesByCategorySeq(@Param("parent_category_seq_name") String parent_category_seq_name);

    List<Shoes> getAllShoesWithReviews();
    // List<Shoes> getShoess();

   

   List<Shoes> getShoesByCategorySeqtop(@Param("parent_category_seq_name") String parent_category_seq_name);

    List<Reviews> selectReviewsByShoeSeq(@Param("shoe_seq") int shoe_seq);

    // 카테고리별로 키워드 정보를 집계하는 메서드


    List<Shoes>  getShoesByCategorySeqs(@Param("category_seq") int category_seq); 
    
    List<Shoes> getTopThreeShoesByCategory(@Param("parent_category_seq_name") String parent_category_seq_name);

    // List<Reviews> selectReviewsForShoe(@Param("shoe_seq") int shoe_seq);

}
