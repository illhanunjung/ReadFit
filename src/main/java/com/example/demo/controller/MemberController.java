package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender mailSender;


    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();

      
        
    
    }@PostMapping("/api/adminlogin")
    public String loginAdmin(HttpSession session, @RequestBody HashMap<String, Object> member) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(member);
        JsonObject json = jsonElement.getAsJsonObject();
    
        String mem_id = json.get("mem_id").getAsString();
        String mem_pw = json.get("mem_pw").getAsString();
        Member mem = new Member(mem_id, mem_pw);
    
        Member loginMember = memberMapper.memberSelect(mem);
        if (loginMember != null) {
            if (loginMember.getMem_role() == 0) {
                session.setAttribute("loginMember", loginMember);
                
                JsonObject resultJson = new JsonObject();
                resultJson.addProperty("id", loginMember.getMem_id());
                resultJson.addProperty("name", loginMember.getMem_name());
                resultJson.addProperty("birth", loginMember.getMem_birth());
                resultJson.addProperty("profile", loginMember.getMem_profile());
                resultJson.addProperty("phone", loginMember.getMem_phone());
                resultJson.addProperty("role", loginMember.getMem_role());
                return resultJson.toString();
            } else if (loginMember.getMem_role() == 2) {
                return "2";
                // "정지된 회원입니다."
            } else if (loginMember.getMem_role() == 1) {
                return "1";
                // "일반회원 입니다."
            }
        }
        return "3";
        //"로그인 실패"
    }

    @PostMapping("/api/login")
    public String login(HttpSession session, @RequestBody HashMap<String, Object> member) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(member);
        JsonObject json = jsonElement.getAsJsonObject();
    
        String mem_id = json.get("mem_id").getAsString();
        String mem_pw = json.get("mem_pw").getAsString();
        Member mem = new Member(mem_id, mem_pw);
    
        Member loginMember = memberMapper.memberSelect(mem);
        if (loginMember != null) {
            if (loginMember.getMem_role() == 1) {
                session.setAttribute("loginMember", loginMember);
                
                JsonObject resultJson = new JsonObject();
                resultJson.addProperty("id", loginMember.getMem_id());
                resultJson.addProperty("name", loginMember.getMem_name());
                resultJson.addProperty("birth", loginMember.getMem_birth());
                resultJson.addProperty("profile", loginMember.getMem_profile());
                resultJson.addProperty("phone", loginMember.getMem_phone());
                resultJson.addProperty("role", loginMember.getMem_role());
                return resultJson.toString();
            } else if (loginMember.getMem_role() == 2) {
                return "2";
                // "정지된 회원입니다."
            } else if (loginMember.getMem_role() == 0) {
                return "0";
                // "관리자 입니다."
            }
        }
        return "3";
        //"로그인 실패"
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
        Member findMemberEmail = memberMapper.memberFindEmail(member);
        if (findMemberEmail != null ) {

            String newPassword = generateRandomPassword();
            
            member.setMem_pw(newPassword);
            memberMapper.updatePassword(member);

            // System.out.println(member.getMem_id() + "의 새로운 비밀번호는 " + newPassword + "입니다.");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(findMemberEmail.getMem_email());
            message.setSubject("[ReadFit]" + member.getMem_id()+"님의 새로운 비빌번호가 발급되었습니다.");
            message.setText("회원님의 새로운 비밀번호는 [ " + newPassword + " ] 입니다.");
            mailSender.send(message);

            return ResponseEntity.ok(findMemberEmail);
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

// 랜덤한 비밀번호 생성 메소드
private String generateRandomPassword() {
    String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String specialCharacters = "!@#$%^&*()-_=+";
    String allCharacters = upperCaseLetters + lowerCaseLetters + numbers + specialCharacters;

    StringBuilder password = new StringBuilder();

    for (int i = 0; i < 10; i++) { // 10자리의 랜덤 비밀번호 생성
        int index = (int) (Math.random() * allCharacters.length());
        password.append(allCharacters.charAt(index));
    }

    return password.toString();
}


}