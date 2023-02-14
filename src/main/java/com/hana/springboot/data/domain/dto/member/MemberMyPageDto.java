package com.hana.springboot.data.domain.dto.member;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class MemberMyPageDto {

    public String memberCode; // 회원번호
    public String name; // 사용자이름
    public String loginId; //로그인 아이디
    public String password; //비밀번호
    public String email; // 이메일
    public LocalDateTime birthDay; //생일
    public MemberType memberType; // 회원구분

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
