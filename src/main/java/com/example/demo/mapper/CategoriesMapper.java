package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Categories;
import com.example.demo.model.Shoes;

@Mapper
public interface CategoriesMapper {
    
    List<Categories> getCategoriesIdx(int category_seq);

    List<Categories> getCategories();




}
