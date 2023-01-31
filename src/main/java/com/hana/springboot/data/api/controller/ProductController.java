package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.eunmClass.MemberType;
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

        return "/products/new";
    }

    @PostMapping("/new")
    public String productAdd(@SessionAttribute(name = "member", required = false) MemberLoginDto member, Model model) {
        String memberCode = member.getMemberCode();

        productService.addProduct(memberCode);

        return "redirect:/product/sellerPage";

    }


}
