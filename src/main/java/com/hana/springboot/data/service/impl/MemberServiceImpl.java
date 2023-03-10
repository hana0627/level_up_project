package com.hana.springboot.data.service.impl;

import com.hana.springboot.data.dao.queryRepository.MemberQueryRepository;
import com.hana.springboot.data.dao.repository.MemberRepository;
import com.hana.springboot.data.domain.baseEntity.CodeGenerator;
import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.member.MemberMyPageDto;
import com.hana.springboot.data.domain.dto.member.MemberSaveDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberType;
import com.hana.springboot.data.service.MemberService;
import com.hana.springboot.global.aop.annotation.TimeCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    private final PasswordEncoder passwordEncoder;

    // 회원저장Logic
    @Transactional
    public Long saveMember(MemberSaveDto dto) {
        log.info("==service==");
        // 중복된 아이디면 회원가입불가처리
        Optional<Member> optionalMember = memberRepository.findByLoginIdAndIsVisibleAndIsDelete(dto.loginId,true,false);
        if(optionalMember.isPresent()) {
            log.info("==service2==");
            return null;
        }

        dto.memberType = MemberType.USER;
        dto.memberCode = CodeGenerator.generateMemberCode();
        dto.setPassword(passwordEncoder.encode(dto.password));

//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.map(dto,Member.class);

        Member member = dto.toEntity();
        member.isVisibleTrueAndIdDeleteFalse();
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    // 로그인 성공로직
    @TimeCheck
    public MemberLoginDto loginMember(MemberLoginDto dto) {
        log.info("==서비스==");
        // 아이디로 있는 회원인지 조회 후,
        Optional<Member> optionalMember = memberRepository.findByLoginIdAndIsVisibleAndIsDelete(dto.loginId,true,false);
        if(optionalMember.isEmpty()){
            return null;
        }
        //조회한 회원의 비밀번호와 입력한 비밀번호가 일치하는지
        Member findMember = optionalMember.get();
        if(!findMember.getPassword().equals(dto.password)){
            return null;
        }

        MemberLoginDto result = new MemberLoginDto(findMember);
        return result;




//        Optional<MemberLoginDto> optionalMember = memberQueryRepository.findByQueryDsl(dto.loginId, true, false);
//        if(optionalMember.isEmpty()) {
//            return null;
//        }
//        MemberLoginDto result = optionalMember.get();
//        if(!result.password.equals(dto.password)) {
//            return null;
//        }
//        return result;

    }


    public List<Member> findAllMembers() {
        return memberRepository.findAllByIsVisibleAndIsDelete(true,false);
    }

    public MemberMyPageDto myPage(String loginId) {
        Optional<Member> optionalMember = memberRepository.findByLoginIdAndIsVisibleAndIsDelete(loginId, true, false);

        Member findMember = optionalMember.orElse(null);
        assert findMember != null;
        return new MemberMyPageDto(findMember);
    }

    @Transactional
    public MemberSaveDto updateMember(MemberSaveDto dto) {

        Optional<Member> optionalMember = memberRepository.findByLoginIdAndIsVisibleAndIsDelete(dto.loginId, true, false);
        Member findMember = optionalMember.orElse(null);

        assert findMember != null;
        findMember.isVisibleFalse();
        findMember.isDeleteTrue();

        Member updateMember = dto.toEntity();
        updateMember.isVisibleTrueAndIdDeleteFalse();
        Member saveMember = memberRepository.save(updateMember);

        return new MemberSaveDto(saveMember);


    }

    @Override
    public Member duplicateMember(String loginId) {
        Optional<Member> optional = memberRepository.findByLoginIdAndIsVisibleAndIsDelete(loginId, true, false);
        return optional.orElse(null);
        // return optional.orElseThrow(RuntimeException::new);
    }
}
