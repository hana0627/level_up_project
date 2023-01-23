package com.hana.springboot.data.domain.entity;

import com.hana.springboot.data.domain.baseEntity.BaseEntity;
import com.hana.springboot.data.domain.eunmClass.MemberPosition;
import com.hana.springboot.data.domain.eunmClass.MemberPositionConverter;
import com.hana.springboot.data.domain.eunmClass.MemberStatus;
import com.hana.springboot.data.domain.eunmClass.MemberStatusConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15)
    private String name; // 사용자이름
    @Column(length = 20)
    private String loginId; //로그인 아이디
    @Column(length = 20)
    private String password; //비밀번호
    @Column(length = 100)
    private String email; // 이메일
    private LocalDateTime birthDay; //생일

    @Column(length = 30)
    @Convert(converter = MemberPositionConverter.class)
    private MemberPosition position; // 직급
    @Column(length = 30)
    @Convert(converter = MemberStatusConverter.class)
    private MemberStatus memberStatus; // 관리자인지 일반사용자인지

    @Builder
    public Member(String name, String loginId, String password, String email, LocalDateTime birthDay, MemberPosition position, MemberStatus memberStatus) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.birthDay = birthDay;
        this.position = position;
        this.memberStatus = memberStatus;
    }
}
