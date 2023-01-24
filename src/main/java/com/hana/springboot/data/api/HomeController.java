package com.hana.springboot.data.api;

import com.hana.springboot.data.dao.repository.MemberRepository;
import com.hana.springboot.data.domain.dto.MemberLoginDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Slf4j
public class HomeController {

//    @RequestMapping("/")
//    public String home() {
//        log.info("헬로컨트롤러");
//        return "/home";
//    }




    @RequestMapping("/")
    public String home(@SessionAttribute(name = "member", required = false) MemberLoginDto member, Model model) {

        log.info("헬로컨트롤러");
        if(member == null) {
            return "/home";
        }


        model.addAttribute("member", member);
        //로그인 한 회원이면서 일반사용자인경우
        if(member.getMemberStatus().equals(MemberStatus.USER)) {
            return "/userHome";
        }
        //로그인 한 회원이면서 관리자인경우
        if(member.getMemberStatus().equals(MemberStatus.ADMIN)) {
            return "/adminHome";
        }
        return "/home";
    }
}