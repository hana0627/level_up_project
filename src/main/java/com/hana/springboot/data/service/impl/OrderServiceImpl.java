package com.hana.springboot.data.service.impl;

import com.hana.springboot.data.dao.queryRepository.OrderQueryRepository;
import com.hana.springboot.data.dao.repository.OrderRepository;
import com.hana.springboot.data.dao.repository.ProductRepository;
import com.hana.springboot.data.domain.baseEntity.CodeGenerator;
import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.order.OrderCreateDto;
import com.hana.springboot.data.domain.dto.product.ProductDetailDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.entity.Order;
import com.hana.springboot.data.domain.entity.Product;
import com.hana.springboot.data.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    private final OrderQueryRepository orderQueryRepository;

    @Override
    @Transactional
    public String createOrder(ProductDetailDto dto, MemberLoginDto member) {

        // 주문수량을 변수로 선언
        Integer amount = dto.getQuantity();

        // 상품을 조회
        Optional<Product> optional = productRepository.findByProductCodeAndIsVisibleAndIsDelete(dto.getProductCode(), true, false);
        Product product = optional.orElse(null);
        ProductSaveDto productSaveDto = new ProductSaveDto(product);


        // 상품 재고 변경
        int restStock = product.getQuantity() - amount;
        if(restStock < 0){
            throw new RuntimeException("구매하려는 수량이 재고보다 많습니다.");
        }

       //기존상품은 화면단에 보이지 않게 처리하고 영속성컨텍스트 초기화
        product.isDeleteTrue();
        product.isVisibleFalse();

        // 변경된 재고로 상품 재등록
        Product newProduct = productSaveDto.toEntity();

        newProduct = productRepository.save(newProduct);
        newProduct.changeQuantity(restStock);
        newProduct.isVisibleTrue();
        newProduct.isDeleteFalse();


        // 주문코드 생성
        StringBuilder sb = CodeGenerator.generateOrderCode();

        StringBuilder count = orderQueryRepository.todayOrderCount(LocalDateTime.now());
        String orderCode = sb.append(count).toString();

        //주문생성
        OrderCreateDto orderDto = new OrderCreateDto(member,product,orderCode,amount);
        Order order = orderDto.toEntity();
        order.isVisibleTrue();
        order.isDeleteFalse();


        return orderRepository.save(order).getOrderCode();


    }
}
