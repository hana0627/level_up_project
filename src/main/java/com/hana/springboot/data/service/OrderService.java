package com.hana.springboot.data.service;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.product.ProductDetailDto;
import com.hana.springboot.data.domain.entity.Member;

public interface OrderService {
    void createOrder(ProductDetailDto dto, MemberLoginDto member);
}
