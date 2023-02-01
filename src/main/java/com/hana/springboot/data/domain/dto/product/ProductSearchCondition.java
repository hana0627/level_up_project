package com.hana.springboot.data.domain.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductSearchCondition {

    private String searchName; //이름으로 검색
    private LocalDateTime startDate; // 검색 시작날짜
    private LocalDateTime endDate; // 검색 종료날짜



}
