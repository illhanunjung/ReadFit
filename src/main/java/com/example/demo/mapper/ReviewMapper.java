package com.example.demo.mapper;

import com.example.demo.model.Reviews;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {
    
    @Select("SELECT review, review_rating FROM REVIEWS WHERE shoe_seq = #{shoeSeq}")
    List<Reviews> getTopReviewsByShoeSeq(@Param("shoeSeq") int shoeSeq);
}

