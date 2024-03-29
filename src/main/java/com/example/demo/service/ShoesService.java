package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ShoesMapper;
import com.example.demo.model.Shoes;

@Service
public class ShoesService {

    @Autowired
    private ShoesMapper shoesMapper;

    public List<Shoes> getShoesByCategorySeq(String parent_category_seq_name) {
        return shoesMapper.getShoesByCategorySeq(parent_category_seq_name);
    }

    //   public List<Shoes> getShoess() {
    //     return shoesMapper.getShoess();
    // }

    public List<Shoes> getAllShoesWithReviews() {
        return shoesMapper.getAllShoesWithReviews();
    }
}

