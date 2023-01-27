package com.hana.springboot.data.service;

import com.hana.springboot.data.domain.dto.MemberLoginDto;
import com.hana.springboot.data.domain.dto.MemberMyPageDto;
import com.hana.springboot.data.domain.dto.MemberSaveDto;
import com.hana.springboot.data.domain.entity.Member;

import java.util.List;

public interface MemberService {
    Long saveMember(MemberSaveDto dto);
    MemberLoginDto loginMember(MemberLoginDto dto);
    List<Member> findAllMembers();
    MemberMyPageDto myPage(String loginId);
    MemberSaveDto updateMember(MemberSaveDto dto);

}
