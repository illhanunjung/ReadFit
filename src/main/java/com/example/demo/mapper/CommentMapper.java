package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Comment;

@Mapper
public interface CommentMapper {
    List<Comment> getCommentsByReviewIdx(int board_seq);

    // CommentMapper.java에 메서드 추가
    void addComment(Comment comment);

    // 기존의 메서드들은 그대로 유지하고 아래의 메서드 추가
    // @Delete("DELETE FROM COMMENTS WHERE comment_seq = #{comment_seq}")
    // void deleteCommentBySeq(@Param("comment_seq") int commentSeq);
    void deleteCommentBySeq(int comment_seq);

    void commentEdit(String commentText, int comment_seq);
}
