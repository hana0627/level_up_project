package com.hana.springboot.data.domain.entity;

import com.hana.springboot.data.domain.baseEntity.BaseEntity;
import com.hana.springboot.data.domain.eunmClass.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String memberCode; // 회원코드
    @Column(length = 15)
    private String productCode; // 상품코드

    @Column(length = 40)
    private OrderStatus orderStatus; // 주문상태

    @Builder
    public Order(String memberCode, String productCode, OrderStatus orderStatus) {
        this.memberCode = memberCode;
        this.productCode = productCode;
        this.orderStatus = orderStatus;
    }
}
