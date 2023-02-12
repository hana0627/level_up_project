package com.hana.springboot.data.service.impl;

import com.hana.springboot.data.dao.queryRepository.ProductQueryRepository;
import com.hana.springboot.data.dao.repository.ProductRepository;
import com.hana.springboot.data.domain.dto.product.*;
import com.hana.springboot.data.domain.entity.Product;
import com.hana.springboot.data.service.ProductFileService;
import com.hana.springboot.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.hana.springboot.data.domain.baseEntity.CodeGenerator.generateProductCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductFileService productFileService;
    // Service간의 계층을 두어서 상품등록과 이미지등록이 같은 transaction 안에서 동작하게 하였습니다.

    private final ProductQueryRepository productQueryRepository;

    // == 상품 판매자용 logic - start == //
    @Override
    @Transactional
    public Product addProduct(String memberCode, ProductSaveDto dto) {
        dto.memberCode = memberCode;

        dto.productCode = generateProductCode(dto.memberCode);
        productFileService.saveProductImage(dto.attachFile, dto.productCode);

        Product product = dto.toEntity();
        product.isVisibleTrue();
        product.isDeleteFalse();

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Page<ProductListDto> findAll(String memberCode, Pageable pageable) {

        PageRequest request = pageRequest(pageable, 50);

        // 사진정보와 상품정보를 한번에 가지고 오기 위하여 테이블을 각각 조회하기보다는
        // queryDsl을 이용하여 join쿼리를 사용
        Page<ProductListDto> products = productQueryRepository.findAllByMemberCode(memberCode, request);


        return products;

    }

    @Override
    public ProductDetailDto findOne(String productCode) {
        // 사진정보와 상품정보를 한번에 가지고 오기 위하여 테이블을 각각 조회하기보다는
        // queryDsl을 이용하여 join쿼리를 사용
        Optional<ProductDetailDto> optional = productQueryRepository.findOne(productCode);
        return optional.orElse(null);
    }
    
    @Override
    @Transactional
    public Long updateProduct(String memberCode, ProductUpdateDto dto) {
        // 기존의 상품은 isVisible false , isDelete true로 설정한뒤
        // 새로운 상품을 저장하는 방식으로 작성하였음


//        List<Product> optional = productRepository.findByProductCodeAndIsVisibleAndIsDelete(dto.getProductCode(), true, false);
//        if(optional.size() != 1) {
//            throw new RuntimeException("조회결과가 없거나 조회건수가 1건 이상입니다.");
//        }
//        Product findProduct = optional.get(0);

        Optional<Product> optional = productRepository.findByProductCodeAndIsVisibleAndIsDelete(dto.productCode, true, false);
        Product findProduct = optional.orElse(null);

        findProduct.isVisibleFalse();
        findProduct.isDeleteTrue();
        //상품조회 end

        //상품저장 start
        dto.memberCode = memberCode;
        Product updateProduct = dto.toEntity();

        updateProduct.isVisibleTrue();
        updateProduct.isDeleteFalse();
        productRepository.save(updateProduct);
        //상품저장 end

        return updateProduct.getId();
    }

    // == 상품 판매자용 logic - end == //

    // == 고객용 logic - start ==/
    @Override
    public Page<ProductListDto> findAll(Pageable pageable) {
        PageRequest request = pageRequest(pageable, 50);


        Page<ProductListDto> products = productQueryRepository.findAll(request);
        return products;
    }

    @Override
    public Page<ProductListDto> findAllWithCondition(Pageable pageable, ProductSearchCondition condition) {
        PageRequest request = pageRequest(pageable, 50);


        Page<ProductListDto> products = productQueryRepository.findAllWithCondition(request, condition);
        return products;
    }


    /**
     * 페이징 처리 method
     * 중복사용이 많아서 별도의 method로 추출하였음
     */
    private static PageRequest pageRequest(Pageable pageable, int pageSize) {
        int current_page = pageable.getPageNumber() < 1 ? 0 : pageable.getPageNumber() - 1;
        return PageRequest.of(current_page, pageSize);
    }
}
