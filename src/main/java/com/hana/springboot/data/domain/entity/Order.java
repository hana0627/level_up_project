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
@Table(name="ORDERS")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String memberCode; // 회원코드
    @Column(length = 15)
    private String productCode; // 상품코드

    @Column(length = 15)
    private String orderCode; // 주문코드

    private Integer amount; // 주문수량
    @Column(length = 40)
    private OrderStatus orderStatus; // 주문상태

    private Integer totalPrice; // 총 금액

    @Builder
    public Order(String memberCode, String productCode, String orderCode, OrderStatus orderStatus, Integer amount, Integer totalPrice) {
        this.memberCode = memberCode;
        this.productCode = productCode;
        this.orderCode = orderCode;
        this.orderStatus = orderStatus;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }


//
//    //주문정보 생성
//    public void createOrder(Member member, Product product, orderInfo order) {
//        this.memberCode = member.getMemberCode();
//        this.productCode = product.getProductCode();
//        this.orderCode = order.getOrderCode();
//        this.amount = order.getAmount();
//        this.orderStatus = OrderStatus.ORDER;
//    }
//
//
//    @Getter
//    private class orderInfo {
//        private String orderCode; // 주문코드
//        private Integer amount; // 주문수량
//        private OrderStatus orderStatus; // 주문상태
//    }


}
