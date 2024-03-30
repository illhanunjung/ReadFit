package com.example.demo.mapper;

import org.apache.ibatis.annotations.*;

import com.example.demo.model.Favorites;

import java.util.List;

@Mapper
public interface FavoritesMapper {

    @Select("SELECT * FROM FAVORITES WHERE mem_id = #{mem_id} AND shoe_seq = #{shoe_seq}")
    Favorites findByMemIdAndShoeSeq(@Param("mem_id") String mem_id, @Param("shoe_seq") int shoe_seq);

    @Insert("INSERT INTO FAVORITES (mem_id, shoe_seq) VALUES (#{mem_id}, #{shoe_seq})")
    void addFavorite(Favorites favorites);

    @Delete("DELETE FROM FAVORITES WHERE  mem_id = #{mem_id} AND shoe_seq = #{shoe_seq}")
    void removeFavorite( @Param("mem_id") String mem_id, @Param("shoe_seq") int shoe_seq);

    @Select("SELECT * FROM FAVORITES WHERE mem_id = #{mem_id}")
    List<Favorites> findByMemId(@Param("mem_id") String mem_id);
}
