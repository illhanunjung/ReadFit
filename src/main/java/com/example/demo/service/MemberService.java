package com.example.demo.service;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<Member> getAllMembers() {
        return memberMapper.getAllMembers();
    }

    // 회원 등록
    public void registerMember(Member member) {
        memberMapper.registerMember(member);
    }

    // 아이디 중복 체크
    public boolean checkId(String memId) {
        return memberMapper.checkId(memId) == null;
    }
}
