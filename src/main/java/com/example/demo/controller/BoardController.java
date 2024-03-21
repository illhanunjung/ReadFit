package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.model.Board;
import com.example.demo.model.Comment;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentMapper comment;

    @GetMapping("/boards")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

       @GetMapping("/boards/{board_seq}")
    public ResponseEntity<Object> getBoardAndCommentsByReviewIdx(@PathVariable int board_seq) {
        Board board = boardService.getBoardByReviewIdx(board_seq);
        List<Comment> commentList = comment.getCommentsByReviewIdx(board_seq);
        System.out.println("commentList 값입니다 :" + commentList.toString());
        if (board != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("board", board);
            response.put("comments", commentList);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
    }
}
}
