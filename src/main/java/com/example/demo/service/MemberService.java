package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Member;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<Member> getAllMembers() {
        return memberMapper.getAllMembers();
    }

    // 회원 등록
    public void registerMember(Member member) throws Exception {
        memberMapper.registerMember(member);
    }


    // 아이디 중복 체크
    public boolean checkId(String memId) {
        Integer count = memberMapper.checkId(memId);
        return count != null && count > 0;
    }
    
    // 해당 멤버를 영구정지 상태로 변경
    public void suspendMember(String memId) {
        memberMapper.suspendMember(memId); 
    }

    // 해당 멤버의 정지 상태를 해제
    public void unsuspendMember(String memId) {
        memberMapper.unsuspendMember(memId); 
    }


}
