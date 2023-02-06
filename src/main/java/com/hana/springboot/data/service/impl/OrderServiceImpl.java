package com.hana.springboot.data.service.impl;

import com.hana.springboot.data.dao.repository.OrderRepository;
import com.hana.springboot.data.dao.repository.ProductRepository;
import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.product.ProductDetailDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.entity.Product;
import com.hana.springboot.data.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createOrder(ProductDetailDto dto, MemberLoginDto member) {

        // 주문수량을 변수로 선언
        Integer amount = dto.getQuantity();
        // 상품을 조회
        Optional<Product> optional = productRepository.findByProductCodeAndIsVisibleAndIsDelete(dto.getProductCode(), true, false);
        Product product = optional.orElse(null);


        // 상품 재고 변경
        int restStock = product.getQuantity() - amount;
        if(restStock < 0){
            throw new RuntimeException("구매하려는 수량이 재고보다 많습니다.");
        }

        // 변경된 재고로 상품 재등록후 기존 상품은 isVisible false, isDelete true 처리

        Product newProduct = productRepository.save(product);
        newProduct.changeQuantity(restStock);


        // 회원코드 조회
        member.getMemberCode();

        LocalDateTime temp = LocalDateTime.now();
        StringBuilder code = new StringBuilder("");
        code.append(temp.getYear())
                .append(temp.getMonthValue())
                .append(temp.getDayOfMonth());
        log.info("여기한번 확인 -> {}", code);


    }
}
