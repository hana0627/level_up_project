package com.hana.springboot.data.api.controller;

import com.hana.springboot.data.domain.dto.product.ProductDetailDto;
import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.domain.dto.product.ProductSearchCondition;
import com.hana.springboot.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products/member")
public class ProductMemberController {

    private final ProductService productService;

    @GetMapping("/list")
    public String productList(Model model, Pageable pageable) {
        log.info("==controller==");

        Page<ProductListDto> products = productService.findAll(pageable);
        model.addAttribute("products", products);

        return "/products/members/productList";
    }

    @GetMapping("findAll/search")
    public String productSearch(@RequestParam ProductSearchCondition condition, Model model, Pageable pageable) {
        // 전체 결과를 보여주는 메소드에 검색조건을 담은 파라미터 한개만 더 추가한것 외엔 완전히 동일한 메소드.
        // 메소드를 한개로 통합하여
        // condition의 빈값 여부에 따라서 동적쿼리를 수행하는 방법에 대해서 고민을 많이 해보았음.

        // 결국 메소드를 분리한 이유

        // 1. 컨트롤러 메소드 이름의 가독성
        //  => productList 라는 이름으로 기존의 전체 상품을 출력하는 logic을 담고 있으나,
        //     특정 결과값만 추출해서 보여주는 logic도 productList라는 이름을 공유하면 유지보수 측면에서 다소 좋아보이지 않았음

        // 2. 비지니스로직과 서비스 한 메소드의 너무 강한 결합도
        //  => 어쨌든 두가지 요청사항은 두가지. 요구사항의 변경이 있을경우 그에 유연하게 대응해야 한다고 판단하였음
        //  => 현재 서비스로직에서 페이징에 관한 처리를 진행중인데,
        //     만약 요구사항이 전체조회는 50건씩, 검색조건은 20건씩 보여주세요.로 바뀐다면 구현을 다시 해야하는 상황이 발생
        //     혹은 이것이 전체 조회인지, 검색상황인지를 담는 파라미터를 추가하여 조건에 따라서 세부사항을 분리할 순 있겠으나
        //     가독성이나 유지보수 측면에서 별로 좋지 않다고 판단하였음.


        Page<ProductListDto> products = productService.findAllWithCondition(pageable, condition);
        model.addAttribute("products", products);

        return "/products/members/productList";
    }


    @GetMapping("/{productCode}/info")
    public String productDetail(@PathVariable("productCode") String productCode, Model model){
        ProductDetailDto product = productService.findOne(productCode);
        // 관리자용 상품 조회메소드를 재사용했음. 스스로도 안티패턴이라고 느껴짐
        // 반환값만 조금 다른 완전히 동일한 메소드
        // 설계시 이런상황에 대한 고려가 없어서 컨트롤러에 비지니스 로직이 들어가게 되었음
        // 요구사항이 구체적으로 주어지지 않더라도, 요구사항에 대비하여 좀 더 신중하게 구현해야함을 느낌

        //상품에 대해서 일반 사용자는 몰라도 되는 값은 null로 설정
        product.setQuantity(null);

        model.addAttribute("product", product);

        return "/products/members/productDetail";
    }

}
