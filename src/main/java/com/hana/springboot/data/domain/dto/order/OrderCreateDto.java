package com.hana.springboot.data.domain.dto.order;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.entity.Order;
import com.hana.springboot.data.domain.entity.Product;
import com.hana.springboot.data.domain.eunmClass.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class OrderCreateDto {

    public String memberCode; // 회원코드
    public String productCode; // 상품코드

    public String orderCode; // 주문코드

    public Integer amount; // 주문수량
    public OrderStatus orderStatus; // 주문상태

    private Integer totalPrice; // 총 금액

    public OrderCreateDto(MemberLoginDto member, Product product, String orderCode, Integer amount) {
        this.memberCode = member.memberCode;
        this.productCode = product.getProductCode();
        this.orderCode = orderCode;
        this.amount = amount;
        this.orderStatus = OrderStatus.ORDER;
        this.totalPrice = product.getPrice()*amount;
    }

    public Order toEntity() {
        return Order.builder()
                .memberCode(memberCode)
                .productCode(productCode)
                .orderCode(orderCode)
                .amount(amount)
                .orderStatus(orderStatus)
                .totalPrice(totalPrice).build();
    }
}
