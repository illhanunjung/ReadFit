package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.CategoriesMapper;
import com.example.demo.model.Categories;


@Service
public class CategoriesService {
    
    @Autowired
    private CategoriesMapper categoriesMapper ;

    public List<Categories> getCategoriesIdx(int category_seq) {
        return categoriesMapper.getCategoriesIdx(category_seq);
    }

    public List<Categories> getCategories() {
        return categoriesMapper.getCategories();
    }
}
