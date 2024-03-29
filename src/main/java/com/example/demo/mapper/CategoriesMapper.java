package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Categories;

@Mapper
public interface CategoriesMapper {
    
    List<Categories> getCategoriesIdx(int category_seq);

    List<Categories> getCategories();

    // CommentMapper.java에 메서드 추가
    // void addComment(Comment comment);
}
