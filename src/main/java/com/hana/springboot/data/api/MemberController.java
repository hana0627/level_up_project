package com.hana.springboot.data.api;

import com.hana.springboot.data.domain.dto.MemberSaveDto;
import com.hana.springboot.data.service.MemberService;
import com.hana.springboot.global.aop.annotation.TimeCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("members/new")
    public String createForm() {

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    @TimeCheck
    public String create(MemberSaveDto dto) {
        log.info("컨트롤러");
        memberService.saveMember(dto);
        return "/home";
    }
}