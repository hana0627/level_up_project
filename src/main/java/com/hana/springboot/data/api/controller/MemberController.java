package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.dao.queryRepository.MemberQueryRepository;
import com.hana.springboot.data.dao.repository.MemberRepository;
import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.member.MemberMyPageDto;
import com.hana.springboot.data.domain.dto.member.MemberSaveDto;
import com.hana.springboot.data.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @GetMapping("/new")
    public String createForm(Model model) {

        //model.addAttribute("member", new Member());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Valid MemberSaveDto dto, BindingResult result) {
        log.info("==Controller - start ==");
        if(result.hasErrors()) {
            return "members/createMemberForm";
        }
        Long memberId = memberService.saveMember(dto);
        if(memberId != null) {
            return "redirect:/";
        }

        result.reject("createdFail", "이미 존재하는 아이디입니다.");

        log.info("==Controller - end ==");
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
        log.info("dto.loginId = {}" , dto.loginId);
        log.info("dto.password = {}", dto.password);
        if (result.hasErrors()) {
            return "members/loginForm";
        }


        // 해당 검색조건시 두가지 방법을 테스트해보았습니다.
        // 첫번째는 jpa로 검색 후 dto로 변환하는 방법
        // 두번째는 queryDsl을 이용하여 바로 dto로 받는 방법이였습니다.
        // 결과 =>  테이블이 작아서 그런지 유의미한 성능차이는 없었습니다.
        MemberLoginDto findMember = memberService.loginMember(dto);

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


    @GetMapping("/myPage")
    public String myPageForm(String loginId, Model model) {
        MemberMyPageDto member = memberService.myPage(loginId);

        model.addAttribute("member",member);

        return "/members/myPage";
    }
    @PostMapping("/myPage")
    public String myPage(MemberSaveDto dto, Model model) {

        MemberSaveDto member = memberService.updateMember(dto);
        model.addAttribute("member",member);

        return "redirect:/";
    }


//    @GetMapping("/notAdmin")
//    public String notAdmin(HttpServletRequest request) {
//        return "redirect:/";
//    }
}