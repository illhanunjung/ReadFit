package com.example.demo.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Board;
import com.example.demo.model.Comment;

@Mapper
public interface BoardMapper {
    List<Board> getAllBoards();
    Board getBoardByReviewIdx(int board_seq);
    void deleteBoardBySeq(int boardSeq);
    void deleteCommentsByBoardSeq(int boardSeq);
    void insertBoard(Board board);
    void updateBoard(Board board);
    void increaseClickCount(int board_seq);
    List<Map<Integer, Integer>> getBoardClickCountsAsList();
    List<Comment> getCommentsByBoardSeq(int board_seq);
}
