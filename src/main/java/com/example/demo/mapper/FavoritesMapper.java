package com.example.demo.mapper;

import org.apache.ibatis.annotations.*;

import com.example.demo.model.Favorites;
import com.example.demo.model.Shoes;

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


    @Select("SELECT " +
    "s.shoe_seq, " +
    "s.category_seq, " +
    "c.category, " +
    "s.shoe, " +
    "s.shoe_price, " +
    "s.shoe_img, " +
    "s.parent_category_seq, " +
    "s.parent_category_seq_name, " +
    "IFNULL(COUNT(r.review_seq), 0) AS reviewCount, " +
    "IFNULL(AVG(CAST(r.review_rating AS DECIMAL(10, 2))), 0) AS averageRating, " +
    "ROUND((SUM(CASE WHEN r.review_polarity = 2 THEN 1 ELSE 0 END) * 100) / COUNT(*)) AS positive_percentage, " +
    "ROUND((SUM(CASE WHEN r.review_polarity = 1 THEN 1 ELSE 0 END) * 100) / COUNT(*)) AS negative_percentage, " +
    "ROUND((SUM(CASE WHEN r.review_polarity = 0 THEN 1 ELSE 0 END) * 100) / COUNT(*)) AS neutral_percentage " +
    "FROM " +
    "SHOES s " +
    "INNER JOIN FAVORITES f ON s.shoe_seq = f.shoe_seq " +
    "LEFT JOIN REVIEWS r ON s.shoe_seq = r.shoe_seq " +
    "LEFT JOIN CATEGORIES c ON s.category_seq = c.category_seq " +
    "WHERE " +
    "f.mem_id = #{mem_id} " +
    "GROUP BY " +
    "s.shoe_seq")
    List<Shoes> getFavoriteShoesByMemberId(String mem_id);

    
}
