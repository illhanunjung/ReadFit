package com.example.demo.mapper;

import com.example.demo.model.KeywordCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KeywordsMapper {
    List<KeywordCount> selectTopKeywordsByParentCategory(@Param("parent_category_seq_name") String parent_category_seq_name);
}

