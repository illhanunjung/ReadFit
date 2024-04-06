package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Board;
import com.example.demo.model.Member;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    

    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardService boardService;

    // 모든 회원 정보 조회
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // 회원 영구정지 기능
    @PutMapping("/members/{mem_id}/suspend")
    public ResponseEntity<String> suspendMember(@PathVariable String mem_id) {
        try {
            memberService.suspendMember(mem_id);
            return ResponseEntity.ok("회원이 영구정지 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 영구정지 실패");
        }
    }

    // 회원 정지해제 기능
    @PutMapping("/members/{mem_id}/unsuspend")
    public ResponseEntity<String> unsuspendMember(@PathVariable String mem_id) {
        try {
            memberService.unsuspendMember(mem_id);
            return ResponseEntity.ok("회원 정지가 해제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 정지해제 실패");
        }}

}

