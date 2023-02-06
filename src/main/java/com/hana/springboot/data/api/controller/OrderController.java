package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.product.ProductDetailDto;
import com.hana.springboot.data.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/newOrder")
    public String newOrder(@SessionAttribute(name = "member", required = false) MemberLoginDto member, ProductDetailDto dto, Model model) {

        String orderCode = orderService.createOrder(dto, member);
        model.addAttribute("orderCode", orderCode);
        log.info("결과확인 =>{}", orderCode);
        return "orders/newOrder";

    }

}
