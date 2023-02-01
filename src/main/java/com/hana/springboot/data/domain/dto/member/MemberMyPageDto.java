package com.hana.springboot.data.domain.dto.member;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberMyPageDto {

    private String memberCode; // 회원번호
    private String name; // 사용자이름
    private String loginId; //로그인 아이디
    private String password; //비밀번호
    private String email; // 이메일
    private LocalDateTime birthDay; //생일
    private MemberType memberType; // 회원구분

    public MemberMyPageDto(Member member) {
        this.memberCode = member.getMemberCode();
        this.name = member.getName();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.birthDay = member.getBirthDay();
        this.memberType = member.getMemberType();
    }
}
