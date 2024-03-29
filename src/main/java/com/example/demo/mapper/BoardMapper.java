package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Board;

@Mapper
public interface BoardMapper {
    List<Board> getAllBoards();
    Board getBoardByReviewIdx(int board_seq);
    void deleteBoardBySeq(int boardSeq);
    void deleteCommentsByBoardSeq(int boardSeq);
    void insertBoard(Board board);
    void updateBoard(Board board);
}
