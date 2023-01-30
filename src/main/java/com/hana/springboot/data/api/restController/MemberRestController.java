package com.hana.springboot.data.api.restController;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.service.MemberService;
import com.hana.springboot.data.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberRestController {
    private final MemberService memberService;

    @GetMapping("/memberList")
    public List<Member> memberList() {
        //TODO RealGrid를 이용한 뷰템플릿 추가 개발 필요
        return memberService.findAllMembers();
    }

        /**
         * 회원아이디 중복검증
         */
        @PostMapping("/LoginIdDuplicate")
        public ResponseEntity<Long> loginIdDuplicate(@RequestBody String loginId) {
            Member member = memberService.duplicateMember(loginId);
            if(member == null ) {
                // 중복된 아이디가 아닐경우
                return ResponseEntity.status(HttpStatus.OK).body(0L);
            }
            // 중복된 아이디일경우
            return ResponseEntity.status(HttpStatus.OK).body(member.getId());
    }



}
