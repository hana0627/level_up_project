package com.hana.springboot.data.dao.queryRepository;

import com.hana.springboot.data.domain.dto.MemberMyPageDto;
import com.hana.springboot.data.domain.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.hana.springboot.data.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;


}
