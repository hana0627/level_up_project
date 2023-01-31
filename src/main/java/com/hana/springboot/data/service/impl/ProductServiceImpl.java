package com.hana.springboot.data.service.impl;

import com.hana.springboot.data.dao.queryRepository.ProductQueryRepository;
import com.hana.springboot.data.dao.repository.ProductRepository;
import com.hana.springboot.data.domain.dto.product.ProductFileSaveDto;
import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.entity.Product;
import com.hana.springboot.data.service.ProductFileService;
import com.hana.springboot.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.hana.springboot.data.domain.baseEntity.CodeGenerator.generateProductCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductFileService productFileService;
    // Service간의 계층을 두어서 상품등록과 이미지등록이 같은 transaction 안에서 동작하게 하였습니다.

    private final ProductQueryRepository productQueryRepository;

    @Override
    @Transactional
    public Product addProduct(ProductSaveDto dto) {

        dto.setProductCode(generateProductCode(dto.getMemberCode()));
        productFileService.saveProductImage(dto.getAttachFile(), dto.getProductCode());

        Product product = dto.toEntity();
        product.isVisibleTrue();
        product.isDeleteFalse();

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Page<ProductListDto> findAll(String memberCode, Pageable request) {


        Page<ProductListDto> products = productQueryRepository.findAllByMemberCode(memberCode, request);


        return products;

    }
}
