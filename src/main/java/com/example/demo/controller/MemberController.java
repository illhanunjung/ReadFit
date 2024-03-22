package com.example.demo.controller;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/api/register")
    public void registerMember(@RequestBody Member member) {
        memberService.registerMember(member);
    }

    @GetMapping("/api/checkId/{memId}")
    public boolean checkId(@PathVariable String memId) {
        return memberService.checkId(memId);
    }

     @PostMapping("/api/login")
    public String login(HttpSession session, @RequestBody HashMap<String, Object> member) {
//ResponseEntity<String>

        Gson gson = new Gson();
        JsonElement jsoelement = gson.toJsonTree(member);
        JsonObject json = jsoelement.getAsJsonObject();
        System.out.println(json.get("mem_id").getAsString());

        String mem_id=json.get("mem_id").getAsString() ;
        String mem_pw=json.get("mem_pw").getAsString();
        Member mem = new Member(mem_id,mem_pw);


        Member loginMember = memberMapper.memberSelect(mem);
        if (loginMember != null ) {
            session.setAttribute("loginMember", loginMember);

            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("name", loginMember.getMem_name());
            return resultJson.toString();
        } else {
            return "로그인 실패";
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
    

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
}