package com.hana.springboot.data.domain.dto.member;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberLoginDto {
    public String memberCode; // 회원번호

    public String name; // 사용자이름
    //@NotEmpty
    public String loginId; //로그인 아이디
    //@NotEmpty
    public String password; //비밀번호

    public MemberType memberType; // 회원구분


    public MemberLoginDto(Member member) {
        this.memberCode = member.getMemberCode();
        this.name = member.getName();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.memberType = member.getMemberType();
    }


}
