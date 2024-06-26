package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.Member;

@Mapper
public interface MemberMapper {

    List<Member> getAllMembers();

    // 회원 등록 메소드
    void registerMember(Member member);

    // 아이디 중복 체크 메소드
    Integer checkId(String memId);

    Member members(String mem_id,String mem_pw);

    Member memberSelect(Member member);
    Member memberFindId(Member member);
    Member memberFindEmail(Member member);

    //업데이트 메소드
    void updatePhone(Member member);
    void updatePassword(Member member);
    void updateProfileImage(@Param("mem_id") String memId, @Param("imagePath") String imagePath);

    String getProfileImage(@Param("memId") String memId);

    // 회원 영구정지
    void suspendMember(@Param("mem_id") String memId);

    // 회원 정지 해제
    void unsuspendMember(@Param("mem_id") String memId);

}



