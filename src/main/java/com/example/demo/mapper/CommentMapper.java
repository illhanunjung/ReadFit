package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Comment;

@Mapper
public interface CommentMapper {
    List<Comment> getCommentsByReviewIdx(int board_seq);

    // CommentMapper.java에 메서드 추가
    void addComment(Comment comment);
}
