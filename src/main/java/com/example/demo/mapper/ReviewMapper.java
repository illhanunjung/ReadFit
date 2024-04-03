package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    List<String> ReviewKeywordList(int shoe_seq);
    List<String> ReviewList(String keyword, int shoe_seq);
}
