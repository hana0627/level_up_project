package com.hana.springboot.data.domain.dto.product;

import com.hana.springboot.data.domain.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Getter
@Setter
public class ProductUpdateDto {

    public String memberCode;
    public String productCode; // 상품코드
    @NotEmpty
    public String name; //상품이름
    @PositiveOrZero
    public Integer price; //상품가격
    @PositiveOrZero
    public Integer quantity; //상품수량
    public String description; // 상품설명

    public String filePath; // 이미지경로


    public Product toEntity() {
        return Product.builder()
                .memberCode(memberCode)
                .productCode(productCode)
                .name(name)
                .price(price)
                .quantity(quantity)
                .description(description).build();
    }

    public ProductUpdateDto(Product product) {
        this.memberCode = product.getMemberCode();
        this.productCode = product.getProductCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.productCode = product.getProductCode();
    }

}
