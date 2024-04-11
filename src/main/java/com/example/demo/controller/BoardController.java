package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.BoardMapper;
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
    private BoardMapper boardMapper;

    @Autowired
    private CommentMapper commentMapper;



    
    @GetMapping("/boards")
    public ResponseEntity<Object> getAllBoards() {
        List<Board> boardList = boardService.getAllBoards();
        List<Map<Integer, Integer>> boardClickCountList = boardMapper.getBoardClickCountsAsList();
        Map<String, Object> response = new HashMap<>();
        response.put("boardList", boardList);
        response.put("boardClickCountList", boardClickCountList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/boards/{board_seq}")
    public ResponseEntity<Object> getBoardAndCommentsByReviewIdx(@PathVariable int board_seq, @RequestHeader(value = "loginMember", required = false) String loginMember) {
        Board board = boardService.getBoardByReviewIdx(board_seq);
        List<Comment> commentList = commentMapper.getCommentsByReviewIdx(board_seq);
        int boardFavoriteCount = boardService.getBoardFavoriteCount(board_seq);
        System.out.println("boarFavoriteCount값입니다. : " + boardFavoriteCount);
        System.out.println("loginMember값입니다. : " + loginMember);
        if (board != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("board", board);
            response.put("comments", commentList);
            response.put("favoriteCount", boardFavoriteCount);
            if(loginMember != null){
                boolean isUserInFavoriteBoardTable = boardService.getUserInFavoriteBoardTable(board_seq, loginMember);
                response.put("isUserFavorite", isUserInFavoriteBoardTable);
            }
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/boards/{board_seq}/comments")
    public ResponseEntity<Object> addCommentToBoard(
        @PathVariable int board_seq,
        @RequestBody Map<String, String> payload) {
        String commentText = payload.get("comment"); // 댓글 내용을 가져옵니다.
        String loginMember = payload.get("loginMember"); // 댓글 내용을 가져옵니다.
        
        // 여기서 댓글 등록을 처리합니다.
        // board_seq와 commentText를 이용하여 새로운 댓글을 생성하거나 저장합니다.
        Comment inputComment = new Comment(0, board_seq, loginMember, commentText, LocalDateTime.now());
        commentMapper.addComment(inputComment);
        
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteComment/{commentSeq}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentSeq) {
        System.out.println("여기 들어왔습니까? commentSeq 값은 이것이걸랑요~ : " + commentSeq);
        // 클라이언트로부터 전송된 commentSeq 값을 추출합니다.
        int comment_seq = commentSeq;

        // commentSeq를 사용하여 DB에서 댓글을 삭제하는 로직을 작성합니다.
        // 이 부분은 여러분의 데이터베이스에 맞게 수정해야 합니다.
        
        try {
            // 댓글 삭제
            commentMapper.deleteCommentBySeq(comment_seq);
            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제 중 오류가 발생했습니다.");
        }
    }


    @DeleteMapping("/deletePost/{boardSeq}")
    public ResponseEntity<String> deletePost(@PathVariable int boardSeq) {
        System.out.println("여기 들어왔습니까? boardSeq 값은 이것이걸랑요~ : " + boardSeq);
        int board_seq = boardSeq;
        try {
            // 게시물 삭제
            boardService.deletePost(board_seq);
            return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시물 삭제 중 오류가 발생했습니다.");
        }
    }


    @PostMapping("/boards/{board_seq}/comments/edit")
    public ResponseEntity<Object> editCommentToBoard(
        @PathVariable int board_seq,
        @RequestBody Map<String, String> payload) {
        String commentText = payload.get("comment"); // 댓글 내용을 가져옵니다.
        String comment_seq_str = payload.get("comment_seq"); // 댓글 내용을 가져옵니다.
        int comment_seq = Integer.parseInt(comment_seq_str);
        System.out.println("여기로 제발 넘어와라 지금쯤이면 집에 갔겠다!!!");
        System.out.println("commentText : " + commentText);
        System.out.println("comment_seq : " + comment_seq);
        

        try {
            commentMapper.commentEdit(commentText, comment_seq);
            return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            // 업데이트 과정에서 예외가 발생하면 실패 응답을 반환합니다.
            return ResponseEntity.badRequest().body("댓글 수정에 실패하였습니다.");
        }
    }


    @GetMapping("/Writepost/{board_seq}")
    public ResponseEntity<Board> getBoardBySeq(@PathVariable int board_seq) {
        // System.out.println("여기 들어왔습니까? 게시글 수정하려 합니다!");
        // System.out.println("선택된 board_seq : " + board_seq);
        try {
            // boardSeq를 사용하여 게시물의 내용을 가져옵니다.
            Board editPost = boardService.getBoardByReviewIdx(board_seq);
            // System.out.println("editPost의 정보입니다. : " + editPost.toString());

            return ResponseEntity.ok().body(editPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @PostMapping("/boards/boardWrite")
    public ResponseEntity<?> writeBoard(@RequestBody Board newBoard) {
        Board searchBoard = boardService.getBoardByReviewIdx(newBoard.getBoard_seq());
        try {
            if (searchBoard == null) {
                // searchBoard가 null인 경우, 새 게시물을 추가
                boardMapper.insertBoard(newBoard);
            } else {
                // searchBoard가 null이 아닌 경우, 기존 게시물을 업데이트
                // 필요한 경우, newBoard의 정보로 searchBoard의 필드를 업데이트
                // 예: searchBoard.setTitle(newBoard.getTitle());
                // 모든 필요한 필드를 업데이트한 후 save 호출
                boardMapper.updateBoard(newBoard); // 또는 boardService.saveBoard(searchBoard); 업데이트된 객체를 저장
            }
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/increaseClickCount")
    public ResponseEntity<?> increaseClickCount(@RequestBody Map<String, Integer> payload) {
        int board_seq = payload.get("board_seq");
        System.out.println("board값은 얼마입니까?" + board_seq);

        try {
            boardMapper.increaseClickCount(board_seq); // 클릭 수 증가 처리
            return ResponseEntity.ok().build(); // 성공 응답 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 오류 발생 시 500 응답 반환
        }
    }

        @GetMapping("/boards/{board_seq}/comments")
    public ResponseEntity<?> getCommentsByBoardSeq(@PathVariable int board_seq) {
        try {
            List<Comment> commentList = commentMapper.getCommentsByBoardSeq(board_seq);
            if (!commentList.isEmpty()) {
                return ResponseEntity.ok(commentList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 조회 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/boards/clickedHeart/{board_seq}")
    public ResponseEntity<String> clickedHeart(@PathVariable int board_seq, @RequestHeader("loginMember") String loginMember) {
        try {
            if(loginMember != null){
                boolean isUserInFavoriteBoardTable = boardService.getUserInFavoriteBoardTable(board_seq, loginMember);
                boardService.clickUserFavoriteButton(isUserInFavoriteBoardTable, board_seq, loginMember);
            }
            return ResponseEntity.ok("좋아요 버튼 누르기 설정 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
        }
    }


}
