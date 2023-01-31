package com.hana.springboot.data.domain.entity;

import com.hana.springboot.data.domain.baseEntity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String memberCode; // 회원코드
    @Column(length = 15)
    private String productCode; // 회원코드

    @Column(length = 40)
    private String name; //상품이름

    private Integer price; //상품가격

    private Integer quantity; //상품수량

    @Builder

    public Product(String memberCode, String productCode, String name, Integer price, Integer quantity) {
        this.memberCode = memberCode;
        this.productCode = productCode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
