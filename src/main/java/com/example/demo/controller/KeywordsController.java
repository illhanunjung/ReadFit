package com.example.demo.controller;

import com.example.demo.model.KeywordCount;
import com.example.demo.model.Keywords;
import com.example.demo.service.KeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class KeywordsController {

    private final KeywordsService keywordsService;

    @Autowired
    public KeywordsController(KeywordsService keywordsService) {
        this.keywordsService = keywordsService;
    }

    @GetMapping("/keywords/top/{parent_category_seq_name}")
    public List<KeywordCount> getTopKeywordsByParentCategory(@PathVariable String parent_category_seq_name) {
        return keywordsService.getTopKeywordsByParentCategory(parent_category_seq_name);
    }

    
    @GetMapping("/rboard/keyword/{shoe_seq}")
    public List<Keywords> selectKeywordsByshoeseq(@PathVariable int shoe_seq){
        
        return keywordsService.selectKeywordsByshoeseq(shoe_seq);
    }
}
