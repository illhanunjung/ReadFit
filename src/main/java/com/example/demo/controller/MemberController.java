package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpSession;

@RestController

@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }



    @PostMapping("/api/login")
    public ResponseEntity<?> login(HttpSession session, @RequestBody HashMap<String, Object> member) {
        Gson gson = new Gson();
        JsonObject json = gson.toJsonTree(member).getAsJsonObject();
    
        String mem_id = json.get("mem_id").getAsString();
        String mem_pw = json.get("mem_pw").getAsString();
        Member mem = new Member(mem_id, mem_pw);
    
        Member loginMember = memberMapper.memberSelect(mem);
        if (loginMember != null) {
            JsonObject resultJson = new JsonObject();
            if (loginMember.getMem_role() == 0) {
                resultJson.addProperty("error", "관리자용 로그인을 해주세요.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resultJson.toString());
            }
    
            session.setAttribute("loginMember", loginMember);
            
            resultJson.addProperty("id", loginMember.getMem_id());
            resultJson.addProperty("name", loginMember.getMem_name());
            resultJson.addProperty("birth", loginMember.getMem_birth());
            resultJson.addProperty("profile", loginMember.getMem_profile());
            resultJson.addProperty("phone", loginMember.getMem_phone());
            resultJson.addProperty("role", loginMember.getMem_role());
            return ResponseEntity.ok(resultJson.toString());
        } else {
            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("error", "로그인 실패");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultJson.toString());
        }
    }
        @PostMapping("/api/logout")
        public String logout(HttpSession session) {
            session.getAttribute("loginMember");
            System.out.println("loginMember");
            session.removeAttribute("loginMember");
            return "로그아웃 성공";
        }
        
        @GetMapping("/api/checkLoginStatus")
        public String checkLoginStatus(HttpSession session) {
            
            if (session.getAttribute("loginMember") != null) {
                return "{\"isLoggedIn\": true}";
            } else {
                return "{\"isLoggedIn\": false}";
            }
        }

    @PostMapping("/api/findId")
    public ResponseEntity<?> findId(@RequestBody Member member){
        Member findMemberId = memberMapper.memberFindId(member);
        if (findMemberId != null ) {
            return ResponseEntity.ok(findMemberId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 정보를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/api/findPw")
    public ResponseEntity<?> findPw(@RequestBody Member member){
        Member findMemberPw = memberMapper.memberFindPw(member);
        if (findMemberPw != null ) {
            return ResponseEntity.ok(findMemberPw);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 정보를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/api/members/register")
    public ResponseEntity<?> registerMember(@RequestBody Member member) {
        try {
            memberService.registerMember(member);           
            return ResponseEntity.ok().body("회원가입에 성공하였습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입에 실패하였습니다.");
        }
    }
    

    // 아이디 중복 체크 엔드포인트
    @GetMapping("/api/checkId/{memId}")
    public ResponseEntity<?> checkId(@PathVariable String memId) {
        boolean isAvailable = memberService.checkId(memId);
        return ResponseEntity.ok().body(isAvailable);
    }

    

    @PostMapping("/api/updatePhone")
public ResponseEntity<?> updatePhone(HttpSession session, @RequestBody HashMap<String, String> phoneUpdateRequest) {
    // 세션에서 로그인한 사용자 정보를 가져옵니다.
    Member loginMember = (Member) session.getAttribute("loginMember");
    
    if (loginMember == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
    }
    
    // 요청에서 새 전화번호를 가져옵니다.
    String newPhone = phoneUpdateRequest.get("newPhone");
    
    // 입력값 검증 (실제로는 좀 더 철저한 검증이 필요합니다)
    if (newPhone == null || newPhone.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새 전화번호가 입력되지 않았습니다.");
    }
    
    try {
        // 사용자 정보를 업데이트합니다.
        Member updatedMember = new Member();
        updatedMember.setMem_id(loginMember.getMem_id());
        updatedMember.setMem_phone(newPhone);
        
        // DB에서 전화번호를 업데이트합니다.
        memberMapper.updatePhone(updatedMember);
        
        // 세션 정보를 업데이트합니다.
        session.setAttribute("loginMember", updatedMember);
        
        return ResponseEntity.ok("전화번호가 변경되었습니다.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("전화번호 변경 중 오류가 발생했습니다.");
    }
}

@PostMapping("/api/updatePassword")
public ResponseEntity<?> updatePassword(HttpSession session, @RequestBody HashMap<String, String> passwordUpdateRequest) {
    Member loginMember = (Member) session.getAttribute("loginMember");
    
    if (loginMember == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
    }

    String newPassword = passwordUpdateRequest.get("newPassword");
    
    
    try {
        loginMember.setMem_pw(newPassword); // 실제로는 비밀번호를 해시화해서 저장해야 합니다.
        memberMapper.updatePassword(loginMember);
        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 변경 중 오류가 발생했습니다.");
    }
}


}