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

import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
    
    

    // 회원가입 엔드포인트
@PostMapping("/api/register")
public ResponseEntity<?> registerMember(@RequestBody Map<String, String> payload) {
    Member member = new Member();
    member.setMem_id(payload.get("mem_id")); // 혹은 카카오 API로부터 받은 고유 ID
    member.setMem_pw("default_password"); // 실제 서비스에서는 비밀번호를 설정하는 로직 필요
    member.setMem_name(payload.get("mem_name"));
    member.setMem_birth(payload.get("mem_birth"));
    member.setMem_profile(payload.get("mem_profile"));
    member.setMem_phone(payload.get("mem_phone"));
    // 나머지 기본값으로 설정할 필드 초기화...

    try {
        // 회원가입 서비스 호출
        memberService.registerMember(member);           
        // 회원가입 성공 응답 반환
        return ResponseEntity.ok().body("회원가입에 성공하였습니다.");
    } catch (Exception e) {
        // 예외 발생 시, 회원가입 실패 응답 반환
        return ResponseEntity.badRequest().body("회원가입에 실패하였습니다.");
    }
}

    // 아이디 중복 체크 엔드포인트
    @GetMapping("/checkId/{memId}")
    public ResponseEntity<?> checkId(@PathVariable String memId) {
        boolean isAvailable = memberService.checkId(memId);
        return ResponseEntity.ok().body(isAvailable);
    }

    

}