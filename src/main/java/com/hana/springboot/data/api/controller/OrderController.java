package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.product.ProductDetailDto;
import com.hana.springboot.data.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/newOrder")
    public String newOrder(@SessionAttribute(name = "member", required = false) MemberLoginDto member, ProductDetailDto dto, RedirectAttributes redirectAttributes) {

        String orderCode = orderService.createOrder(dto, member);
        redirectAttributes.addAttribute("orderCode", orderCode);
        return "redirect:/orders/finishOrder";
    }

    @GetMapping("/finishOrder")
    public String finishOrder(@RequestParam String orderCode, Model model) {
        log.info("==컨트롤러 호출==");
        model.addAttribute("orderCode",orderCode);
        log.info("orderCode => {}",orderCode);
        return "/orders/newOrder";
    }


}
