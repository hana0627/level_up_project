package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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
    public String productAdd(@SessionAttribute(name = "member", required = false) MemberLoginDto member, RedirectAttributes redirectAttributes,
                             @Valid ProductSaveDto dto, BindingResult result) {

        if(result.hasErrors()) {
            result.reject("fail", "공란을 채워주세요");
            return "/products/createProductForm";
        }


        String memberCode = member.getMemberCode();
        dto.setMemberCode(memberCode);

        productService.addProduct(dto);

        return "redirect:/products/sellerPage"; // TODO 추후 경로 상품 상세페이로 수정
    }

    @GetMapping("/list")
    public String ProductList(@SessionAttribute(name = "member", required = false) MemberLoginDto member,
                              Model model) {
        String memberCode = member.getMemberCode();

        List<ProductListDto> products = productService.findAll(memberCode);
        model.addAttribute("products", products);
        return "/products/sellerProductList";
    }


}
