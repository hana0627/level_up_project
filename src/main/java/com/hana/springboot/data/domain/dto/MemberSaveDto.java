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
public class MemberSaveDto {


    private String name; // 사용자이름
    private String loginId; //로그인 아이디
    private String password; //비밀번호
    private String email; // 이메일
    private LocalDateTime birthDay; //생일
    private MemberPosition position; // 직급


    public Member toEntity() {
        return Member.builder()
                .name(name)
                .loginId(loginId)
                .password(password)
                .email(email)
                .birthDay(birthDay)
                .position(position)
                .build();
    }

}
