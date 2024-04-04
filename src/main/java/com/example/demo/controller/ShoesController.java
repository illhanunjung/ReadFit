package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FavoriteShoesInfo;
import com.example.demo.model.KeywordCount;
import com.example.demo.model.Shoes;
import com.example.demo.service.ShoesService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ShoesController {

    @Autowired
    private ShoesService shoesService;

    //    @GetMapping("/shoe/{parent_category_seq_name}")
    // public List<Shoes> getShoesByCategorySeq(@PathVariable String parent_category_seq_name) {
    //     return shoesService.getShoesByCategorySeq(parent_category_seq_name);
    // }

    //   @GetMapping("/shoe")
    // public List<Shoes> getShoess() {
    //     return shoesService.getShoess();
    // }

    @GetMapping("/shoe")
    public List<Shoes> getAllShoes() {
        return shoesService.getAllShoesWithReviews();
    }
    
  @GetMapping("/shoe/{parentCategoryName}")
public List<Shoes> getShoesDetailsByCategory(@PathVariable String parentCategoryName) {
    
    return shoesService.getShoesByCategorySeq(parentCategoryName);
}

  // 카테고리명을 기반으로 상위 10개의 상품과 각 상품의 리뷰 정보를 반환
    @GetMapping("/shoe/top/{parentCategoryName}")
    public List<Shoes> getShoesByCategorySeqtop(@PathVariable String parentCategoryName) {
        return shoesService.getShoesByCategorySeqtop(parentCategoryName);
    }

  




@GetMapping("/shoe/{parentCategoryName}/{category_seq}")
public List<Shoes> getShoesByCategorySeqs(@PathVariable int category_seq) {
    
    return shoesService.getShoesByCategorySeqs(category_seq);
}

@GetMapping("/shoes/topthree/{parentCategoryName}")
public List<Shoes> getTopThreeShoesByCategory(@PathVariable String parentCategoryName) {
   
    return shoesService.getTopThreeShoesByCategory(parentCategoryName);
}

}
