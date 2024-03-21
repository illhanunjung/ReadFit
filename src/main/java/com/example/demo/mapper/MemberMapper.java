package com.example.demo.mapper;

import com.example.demo.model.Member;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    List<Member> getAllMembers();

    // 회원 등록 메소드
    void registerMember(Member member);

    // 아이디 중복 체크 메소드
    Member checkId(String memId);

    Member members(String mem_id,String mem_pw);

    Member memberSelect(Member member);
    Member memberFindId(Member member);
    Member memberFindPw(Member member);
}
