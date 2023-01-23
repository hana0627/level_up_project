package com.hana.springboot.data.api;

import com.hana.springboot.data.domain.dto.MemberSaveDto;
import com.hana.springboot.data.service.MemberService;
import com.hana.springboot.global.aop.annotation.TimeCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm() {

        return "members/createMemberForm";
    }

    @PostMapping("/new")
    //@TimeCheck
    public String create(MemberSaveDto dto) {
        log.info("컨트롤러");
        memberService.saveMember(dto);
        log.info("로직끝남");
        return "redirect:/";
    }
}