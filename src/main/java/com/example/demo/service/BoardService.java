package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.Board;
import com.example.demo.model.Comment;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public List<Board> getAllBoards() {
        return boardMapper.getAllBoards();

    }

    public Board getBoardByReviewIdx(int board_seq) {
        return boardMapper.getBoardByReviewIdx(board_seq);
    }

    public void deletePost(int boardSeq) {
        boardMapper.deleteCommentsByBoardSeq(boardSeq);
        boardMapper.deleteBoardBySeq(boardSeq);
    }
    
    public List<Comment> getCommentsByBoardSeq(int board_seq) {
        return boardMapper.getCommentsByBoardSeq(board_seq);
    }

    public int getBoardFavoriteCount(int board_seq) {
        return boardMapper.getBoardFavoriteCount(board_seq);
    }

    public boolean getUserInFavoriteBoardTable(int board_seq, String loginMember) {
        return boardMapper.getUserInFavoriteBoardTable(board_seq, loginMember);
    }

    public void clickUserFavoriteButton(boolean isUserInFavoriteBoardTable, int board_seq, String loginMember){
        if(isUserInFavoriteBoardTable){
            //Delete
            boardMapper.deleteFavoriteByBoardSeqAndLoginMember(board_seq, loginMember);
        }else{
            //Insert
            boardMapper.insertFavoriteByBoardSeqAndLoginMember(board_seq, loginMember);
        }
    }

}
