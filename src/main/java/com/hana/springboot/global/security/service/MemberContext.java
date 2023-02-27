package com.hana.springboot.global.security.service;

import com.hana.springboot.data.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class MemberContext extends User {

    private final Member member;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getLoginId(), member.getPassword(), authorities);
        this.member = member; // 후에 참조하기 위함
    }

    public Member getMember() {
        return member;
    }
}
