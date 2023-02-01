package com.hana.springboot.data.domain.dto.product;

import com.hana.springboot.data.domain.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailDto {

    private String memberCode; // 회원코드
    private String productCode; // 상품코드
    @NotEmpty
    private String name; //상품이름
    @PositiveOrZero
    private Integer price; //상품가격
    @PositiveOrZero
    private Integer quantity; //상품수량
    private String description; // 상품설명

    private String filePath; // 이미지경로


    public Product toEntity() {
        return Product.builder()
                .memberCode(memberCode)
                .productCode(productCode)
                .name(name)
                .price(price)
                .quantity(quantity)
                .description(description).build();
    }

    public ProductDetailDto(Product product) {
        this.memberCode = product.getMemberCode();
        this.productCode = product.getProductCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.productCode = product.getProductCode();
    }

}
