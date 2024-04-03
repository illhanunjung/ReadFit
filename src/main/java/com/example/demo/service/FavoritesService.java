package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.FavoritesMapper;
import com.example.demo.mapper.ShoesMapper;
import com.example.demo.model.Favorites;
import com.example.demo.model.Shoes;

import java.util.List;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesMapper favoriteMapper;



    public void addFavorite(Favorites favorites) {
        favoriteMapper.addFavorite(favorites);
    }

    public void removeFavorite( String mem_id, int shoe_seq) {
        favoriteMapper.removeFavorite( mem_id, shoe_seq);
    }

    public List<Favorites> getFavorites(String mem_id) {
        return favoriteMapper.findByMemId(mem_id);
    }

    public List<Shoes> getFavoriteShoesByMemberId(String mem_id) {
        return favoriteMapper.getFavoriteShoesByMemberId(mem_id);
    }
    

 
}
