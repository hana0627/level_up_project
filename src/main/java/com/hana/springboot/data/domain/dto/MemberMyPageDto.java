package com.hana.springboot.data.domain.dto;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberPosition;
import com.hana.springboot.data.domain.eunmClass.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberMyPageDto {

    private String name; // 사용자이름
    private String loginId; //로그인 아이디
    private String password; //비밀번호
    private String email; // 이메일
    private LocalDateTime birthDay; //생일
    private MemberPosition position; // 직급
    private MemberStatus memberStatus; // 관리자인지 일반사용자인지

    public MemberMyPageDto(Member member) {
        this.name = member.getName();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.birthDay = member.getBirthDay();
        this.position = member.getPosition();
        this.memberStatus = member.getMemberStatus();
    }
}
