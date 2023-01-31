package com.hana.springboot.data.service;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.member.MemberMyPageDto;
import com.hana.springboot.data.domain.dto.member.MemberSaveDto;
import com.hana.springboot.data.domain.entity.Member;

import java.util.List;

public interface MemberService {
    Long saveMember(MemberSaveDto dto);
    MemberLoginDto loginMember(MemberLoginDto dto);
    List<Member> findAllMembers();
    MemberMyPageDto myPage(String loginId);
    MemberSaveDto updateMember(MemberSaveDto dto);

    Member duplicateMember(String loginId);
}
