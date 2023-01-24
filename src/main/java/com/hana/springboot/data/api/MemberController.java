package com.hana.springboot.data.api;

import com.hana.springboot.data.domain.dto.MemberLoginDto;
import com.hana.springboot.data.domain.dto.MemberSaveDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.service.MemberService;
import com.hana.springboot.global.aop.annotation.TimeCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    public String create(@Valid MemberSaveDto dto, BindingResult result) {
        if(result.hasErrors()) {
            return "members/createMemberForm";
        }
        Long memberId = memberService.saveMember(dto);
        if(memberId != null) {
            return "redirect:/";
        }

        result.reject("createdFail", "이미 존재하는 아이디입니다.");
        return "members/createMemberForm";

    }


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new Member());
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid MemberLoginDto dto, Model model, BindingResult result) {
        log.info("==컨트롤러==");
        if (result.hasErrors()) {
            return "members/loginForm";
        }
        MemberLoginDto findMember = memberService.loginMember(dto);
        if(findMember==null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginForm";
        }

        //TODO 세션적용
        return "redirect:/";
    }

}