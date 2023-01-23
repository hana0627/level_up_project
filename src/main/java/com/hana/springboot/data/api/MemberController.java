package com.hana.springboot.data.api;

import com.hana.springboot.data.domain.dto.MemberSaveDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.service.MemberService;
import com.hana.springboot.global.aop.annotation.TimeCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model) {

        model.addAttribute("member", new Member());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    //@TimeCheck
    //TODO 해당 어노테이션 적용시 members/new 템플릿을 찾아가는 이슈
    //service, repository에서는 이상무
    public String create(MemberSaveDto dto) {
        memberService.saveMember(dto);
        return "redirect:/";
    }
}