package com.hana.springboot.data.domain.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;


@NoArgsConstructor
@Getter
@Setter
public class ProductListDto {
    public String memberCode; // 회원코드
    public String productCode; // 상품코드
    public String name; //상품이름
    public Integer price; //상품가격
    public Integer quantity; //상품수량
    public String filePath; // 파일저장경로


}
