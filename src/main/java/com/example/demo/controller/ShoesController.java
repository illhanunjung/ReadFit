package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}