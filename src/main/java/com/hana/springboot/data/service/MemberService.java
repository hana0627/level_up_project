package com.hana.springboot.data.service;

import com.hana.springboot.data.dao.repository.MemberRepository;
import com.hana.springboot.data.domain.dto.MemberSaveDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberStatus;
import com.hana.springboot.global.aop.annotation.TimeCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원저장Logic
    @TimeCheck
    public Long saveMember(MemberSaveDto dto) {
        //dto.setMemberStatus(MemberStatus.USER);
        Member member = dto.toEntity();

        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

}
