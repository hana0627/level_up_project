package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.domain.dto.member.MemberLoginDto;
import com.hana.springboot.data.domain.dto.product.ProductDetailDto;
import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.dto.product.ProductUpdateDto;
import com.hana.springboot.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products/seller")
public class ProductSellerController {

    private final ProductService productService;


    @GetMapping("/sellerPage")
    public String sellerPageForm(Model model){

        return "/products/sellers/sellerPage";
    }

    @GetMapping("/new")
    public String productAddForm() {

        return "/products/sellers/createProductForm";
    }

    @PostMapping("/new")
    public String productAdd(@SessionAttribute(name = "member", required = false) MemberLoginDto member, RedirectAttributes redirectAttributes,
                             @Valid ProductSaveDto dto, BindingResult result) {

        if(result.hasErrors()) {
            result.reject("fail", "공란을 채워주세요");
            return "/products/sellers/createProductForm";
        }


        String memberCode = member.getMemberCode();
        dto.setMemberCode(memberCode);

        productService.addProduct(member.getMemberCode(), dto);

        return "redirect:/products/sellerPage"; // TODO 추후 경로 상품 상세페이로 수정
    }

    @GetMapping("/list")
    public String ProductList(@SessionAttribute(name = "member", required = false) MemberLoginDto member,
                              Model model, Pageable pageable) {
        Page<ProductListDto> products = productService.findAll(member.getMemberCode(), pageable);
        model.addAttribute("products", products);
        return "/products/sellers/sellerProductList";
    }


    @GetMapping("/{productCode}/info")
    public String ProductEditForm(@PathVariable("productCode") String productCode, Model model) {
        log.info("productCode -> {}" , productCode);
        ProductDetailDto product = productService.findOne(productCode);
        model.addAttribute("product",product);
            return "/products/sellers/editProductForm";
}

    @PostMapping("/{productCode}/info")
    public String ProductEdit(@SessionAttribute(name = "member", required = false) MemberLoginDto member, ProductUpdateDto dto) {

        // Optional<Product> 로 조회시 쿼리가 잘못 실행되었을때 did not unique result 와 같은 메세지가 나오는것을
        // List<Product>로 조회하여 오류처리를 좀 더 재미있게 해보았음

        // 성능측정 결과
        // List =>  updateProduct  resultTime = 48
        // Optional =>  updateProduct  resultTime = 53
        // 성능상에 유의미한 차이가 느껴지진 않으며,
        // 오히려 optional -> orElse 의 두줄짜리 코드를
        // list -> if -> get(0)로 라인수도 늘어나고 더 복잡한 느낌이 들었음.
        // 해당방법으로 예외처리를 하지 않는 이유에 대해서 생각하게 된 계기.

        // 파라미터로 받은 정보를 where절에 넣어서 바로 수정쿼리를 날리는것이 아닌
        // 한번 조회후, 예외처리를 한 이후 수정쿼리를 날렸음
        // 영한팀장님의 jpa강의에서 하시던 패턴이며 들으면서 궁금했던 부분이였음.
        // 위의 시도를 통해
        // 마찬가지로 성능이 조금 느려지더라도, 예외상황에 대해서 좀 더 유연하게 대처할 수 있으면
        // 한 메소드는 최소한의 역할과 책임을 가지는것이 더 올바른 코드작성방법이라고 생각하게 됨.

        productService.updateProduct(member.getMemberCode(), dto);

        return "redirect:/products/sellers/sellerPage";
    }



}
