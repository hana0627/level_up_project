package com.hana.springboot.data.dao.queryRepository;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.hana.springboot.data.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;


    public Optional<MemberLoginDto> findByQueryDsl(String loginId, boolean isVisible, boolean isDelete) {
        Optional<MemberLoginDto> result = Optional.ofNullable(
                queryFactory.select(Projections.fields(MemberLoginDto.class,
                        member.memberCode,
                        member.name,
                        member.loginId,
                        member.password,
                        member.memberType
                )).from(member)
                .where(member.loginId.eq(loginId),
                        member.isVisible.eq(isVisible),
                        member.isDelete.eq(isDelete))
                .fetchOne());

        return result;
    }
}
