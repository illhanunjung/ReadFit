package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.Shoes;

@Mapper
public interface ShoesMapper {
    List<Shoes> getShoesByCategorySeq(@Param("parent_category_seq_name") String parent_category_seq_name);

    List<Shoes> getAllShoesWithReviews();
    // List<Shoes> getShoess();
}
