package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.dao.queryRepository.MemberQueryRepository;
import com.hana.springboot.data.dao.repository.MemberRepository;
import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.member.MemberMyPageDto;
import com.hana.springboot.data.domain.dto.member.MemberSaveDto;
import com.hana.springboot.data.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;
    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @GetMapping("/new")
    public String createForm(Model model) {

        //model.addAttribute("member", new Member());
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
        Long memberId = memberServiceImpl.saveMember(dto);
        if(memberId != null) {
            return "redirect:/";
        }

        result.reject("createdFail", "이미 존재하는 아이디입니다.");
        return "members/createMemberForm";

    }


    @GetMapping("/login")
    public String loginForm(Model model) {
        log.info("로그인 getMapping");
        //model.addAttribute("member", new Member());
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid MemberLoginDto dto, Model model,
                        HttpServletRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "members/loginForm";
        }
        MemberLoginDto findMember = memberServiceImpl.loginMember(dto);


        if(findMember==null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("member", findMember);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


    @GetMapping("/notAdmin")
    public String notAdmin(HttpServletRequest request) {
        //TODO 추가작성
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPageForm(String loginId, Model model) {
        MemberMyPageDto member = memberServiceImpl.myPage(loginId);

        model.addAttribute("member",member);

        return "/members/myPage";
    }
    @PostMapping("/myPage")
    public String myPage(MemberSaveDto dto, Model model) {
        System.out.println(dto.getLoginId());

        MemberSaveDto member = memberServiceImpl.updateMember(dto);
        model.addAttribute("member",member);

        return "redirect:/";
    }



}