package com.hana.springboot.data.domain.dto.member;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@NoArgsConstructor
@ToString
public class MemberSaveDto {


    public String memberCode; //회원번호
    @NotEmpty
    public String name; // 사용자이름
    @NotEmpty
    public String loginId; //로그인 아이디
    @NotEmpty
    public String password; //비밀번호
    @Email
    public String email; // 이메일
    public LocalDateTime birthDay; //생일
    public MemberType memberType; //회원구분


    public Member toEntity() {
        return Member.builder()
                .memberCode(memberCode)
                .name(name)
                .loginId(loginId)
                .password(password)
                .email(email)
                .birthDay(birthDay)
                .memberType(memberType)
                .build();
    }

    public MemberSaveDto(Member member) {
        this.memberCode = member.getMemberCode();
        this.name = member.getName();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.birthDay = member.getBirthDay();
        this.memberType = member.getMemberType();
    }
}
