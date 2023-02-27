package com.hana.springboot.global.security.service;

import com.hana.springboot.data.dao.repository.MemberRepository;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.eunmClass.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Optional<Member> optional = memberRepository.findByLoginId(loginId);

        if(optional.isEmpty()) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        if(optional.isPresent()) {
            Member member = optional.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(member.getMemberType().toString()));
            MemberContext memberContext = new MemberContext(member, roles);
            return memberContext;
        }

        return null;
    }
}
