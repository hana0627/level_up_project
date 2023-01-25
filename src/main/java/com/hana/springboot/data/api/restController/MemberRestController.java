package com.hana.springboot.data.api.restController;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberRestController {
    private final MemberService memberService;

    @GetMapping("/memberList")
    public List<Member> notAdmin() {
        //TODO RealGrid를 이용한 뷰템플릿 추가 개발 필요
        return memberService.findAllMembers();
    }



}