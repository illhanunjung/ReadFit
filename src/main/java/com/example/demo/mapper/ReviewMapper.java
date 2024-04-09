package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Reviews;

@Mapper
public interface ReviewMapper {
    
    @Select("SELECT review, review_rating FROM REVIEWS WHERE shoe_seq = #{shoeSeq}")
    List<Reviews> getTopReviewsByShoeSeq(@Param("shoeSeq") int shoeSeq);
    List<String> ReviewKeywordList(int shoe_seq);
    List<String> ReviewList(String keyword, int shoe_seq);
    Map<String, Object> searchReviewSummarybyshoeSeq(int shoe_seq);
    void saveReviewSummary(int shoe_seq, String summary_text);
    
}


