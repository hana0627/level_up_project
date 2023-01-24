package com.hana.springboot.data.service;

import com.hana.springboot.data.dao.repository.MemberRepository;
import com.hana.springboot.data.domain.dto.MemberLoginDto;
import com.hana.springboot.data.domain.dto.MemberSaveDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberStatus;
import com.hana.springboot.global.aop.annotation.TimeCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원저장Logic
    @TimeCheck
    @Transactional
    public Long saveMember(MemberSaveDto dto) {
        // 중복된 아이디면 회원가입불가처리
        Optional<Member> optionalMember = memberRepository.findByLoginId(dto.getLoginId());
        if(optionalMember.isPresent()) {
            return null;
        }



        dto.setMemberStatus(MemberStatus.USER);
        Member member = dto.toEntity();

        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    // 로그인 성공로직
    //@TimeCheck
    public MemberLoginDto loginMember(MemberLoginDto dto) {
        log.info("==서비스==");
        // 아이디로 있는 회원인지 조회 후,
        Optional<Member> optionalMember = memberRepository.findByLoginId(dto.getLoginId());
        if(optionalMember.isEmpty()){
            return null;
        }
        //조회한 회원의 비밀번호와 입력한 비밀번호가 일치하는지
        Member findMember = optionalMember.get();
        if(!findMember.getPassword().equals(dto.getPassword())){
            return null;
        }

        MemberLoginDto result = new MemberLoginDto(findMember);
        return result;
    }

}
