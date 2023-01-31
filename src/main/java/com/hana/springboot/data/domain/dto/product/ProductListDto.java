package com.hana.springboot.data.domain.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
public class ProductListDto {
    private String memberCode; // 회원코드
    private String productCode; // 상품코드
    private String name; //상품이름
    private Integer price; //상품가격
    private Integer quantity; //상품수량
    private String filePath; // 파일저장경로


}
