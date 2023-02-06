package com.hana.springboot.data.dao.queryRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final JPAQueryFactory queryFactory;

    public long todayOrderCount(LocalDateTime ldt) {
//        queryFactory.select()
//                .from()
//                .fetch().size();
        return 1;
    }
}
