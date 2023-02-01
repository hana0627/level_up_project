package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products/member")
public class ProductMemberController {

    private final ProductService productService;

    @GetMapping("/list")
    public String productList(Model model, Pageable pageable) {

        Page<ProductListDto> products = productService.findAll(pageable);
        model.addAttribute("products", products);

        return "products/members/productList";
    }

}
