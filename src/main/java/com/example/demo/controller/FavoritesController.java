package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Favorites;
import com.example.demo.model.Shoes;
import com.example.demo.service.FavoritesService;
import com.example.demo.service.ShoesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")

public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @PostMapping("/favorites/add")
    public void addFavorite(@RequestBody Favorites favorites) {
        favoritesService.addFavorite(favorites);
    }

    @DeleteMapping("favorites/remove")
    public void removeFavorite( @RequestParam String mem_id, @RequestParam int shoe_seq) {
        favoritesService.removeFavorite( mem_id, shoe_seq);
    }

    @GetMapping("/favorites")
    public List<Favorites> getFavorites(@RequestParam String mem_id) {
        return favoritesService.getFavorites(mem_id);
    }

    @GetMapping("/favorites/{mem_id}")
    public ResponseEntity<List<Shoes>> getFavoriteShoes(@PathVariable String mem_id) {
        List<Shoes> favoriteShoes = favoritesService.getFavoriteShoesByMemberId(mem_id);
        return ResponseEntity.ok().body(favoriteShoes);
    }

}
