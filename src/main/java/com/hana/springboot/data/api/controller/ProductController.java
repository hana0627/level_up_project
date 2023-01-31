package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/sellerPage")
    public String sellerPageForm(Model model){

        return "/products/sellerPage";
    }

    @GetMapping("/new")
    public String productAddForm() {

        return "/products/createProductForm";
    }

    @PostMapping("/new")
    public String productAdd(@SessionAttribute(name = "member", required = false) MemberLoginDto member,
                             ProductSaveDto dto, Model model) {
        String memberCode = member.getMemberCode();

        dto.setMemberCode(memberCode);

        System.out.println(dto.toString());
        productService.addProduct(dto);

        return "redirect:/product/sellerPage"; // TODO 추후 경로 상품 상세페이로 수정

    }


}
