package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Member;

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

    // 전화번호 업데이트 메소드
void updatePhone(Member member);

void updatePassword(Member member);

void updateProfileImage(@Param("mem_id") String memId, @Param("imagePath") String imagePath);

String getProfileImage(@Param("memId") String memId);



}
