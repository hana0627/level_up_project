package com.hana.springboot.data.domain.dto;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberLoginDto {
    private String name; // 사용자이름
    @NotEmpty
    private String loginId; //로그인 아이디
    @NotEmpty
    private String password; //비밀번호

    private MemberStatus memberStatus; // 관리자인지 일반사용자인지

    public MemberLoginDto(Member member) {
        this.name = member.getName();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.memberStatus = member.getMemberStatus();
    }
}
