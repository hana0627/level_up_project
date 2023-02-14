package com.hana.springboot.data.domain.dto.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class ProductSearchCondition {

    public String nameSearchCondition; //이름으로 검색
    public LocalDateTime startDate; // 검색 시작날짜
    public LocalDateTime endDate; // 검색 종료날짜



}
