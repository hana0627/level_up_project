package com.hana.springboot.data.domain.entity;

import com.hana.springboot.data.domain.baseEntity.BaseEntity;
import com.hana.springboot.data.domain.eunmClass.MemberType;
import com.hana.springboot.data.domain.eunmClass.MemberTypeConvertor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String memberCode; // 회원코드
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
    @Convert(converter = MemberTypeConvertor.class)
    private MemberType memberType; // 회원구분

    @Builder
    public Member(String memberCode, String name, String loginId, String password, String email, LocalDateTime birthDay, MemberType memberType) {
        this.memberCode = memberCode;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.birthDay = birthDay;
        this.memberType = memberType;
    }

    public void isVisibleTrueAndIdDeleteFalse() {
        isVisibleTrue();
        isDeleteFalse();
    }
}


//    @Column(length = 30)
//    @Convert(converter = MemberPositionConverter.class)
//    private MemberPosition position; // 직급
