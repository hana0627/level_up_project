package com.hana.springboot.data.domain.dto;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberType;
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

    private MemberType memberType; // 회원구분

    public MemberLoginDto(Member member) {
        this.name = member.getName();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.memberType = member.getMemberType();
    }
}
