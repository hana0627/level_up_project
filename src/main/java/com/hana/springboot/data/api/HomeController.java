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

    //TODO 추가개발 해보고 싶은거
    // 로그인 -> jwt 적용. (localhost에서도 가능...? 서버 구축 못하는뎁......)
    // 주소등록 api 직접 처음부터 작업
    // 이메일을 통한 인증

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