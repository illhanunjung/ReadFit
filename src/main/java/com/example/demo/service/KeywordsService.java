package com.example.demo.service;

import com.example.demo.mapper.KeywordsMapper;
import com.example.demo.model.KeywordCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordsService {

    private final KeywordsMapper keywordsMapper;

    @Autowired
    public KeywordsService(KeywordsMapper keywordsMapper) {
        this.keywordsMapper = keywordsMapper;
    }

    public List<KeywordCount> getTopKeywordsByParentCategory(String parent_category_seq_name) {
        return keywordsMapper.selectTopKeywordsByParentCategory(parent_category_seq_name);
    }
}
