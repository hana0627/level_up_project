package com.hana.springboot.data.dao.queryRepository;

import com.hana.springboot.data.domain.entity.QOrder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.hana.springboot.data.domain.entity.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final JPAQueryFactory queryFactory;

    public StringBuilder todayOrderCount(LocalDateTime ldt) {
        Long count = queryFactory.select(Wildcard.count)// //order.count()
                .from(order)
                .where(order.createdAt.goe(ldt.toLocalDate().atStartOfDay()),
                order.createdAt.loe(ldt.plusDays(1).toLocalDate().atStartOfDay()))
                .fetchOne();

        count +=1;
        StringBuilder result = new StringBuilder(String.valueOf(count));


        if(count <100 && count>=10){
            result.insert(0,"0");
        }

        if(count < 10){
            result.insert(0,"00");
        }
//        else{
//            throw new RuntimeException("알 수 없는 오류!");
//        }

        return result;
    }
}
