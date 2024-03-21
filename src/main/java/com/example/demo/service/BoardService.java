package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.Board;

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

}
