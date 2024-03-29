package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Categories;
import com.example.demo.service.CategoriesService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriesController {
    
    @Autowired
    private CategoriesService categoriesService;

     @GetMapping("/categories/{category_seq}")
    public List<Categories>  getCategoriesIdx(@PathVariable int category_seq) {
        return categoriesService.getCategoriesIdx(category_seq);
    }

    
    @GetMapping("/categories")
    public List<Categories>  getCategories() {
        return categoriesService.getCategories();
    }



}
