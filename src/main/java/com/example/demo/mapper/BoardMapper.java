package com.example.demo.mapper;

import com.example.demo.model.Board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    List<Board> getAllBoards();

    Board getBoardByReviewIdx(int board_seq);

}
