package com.hana.springboot.data.service.impl;

import com.hana.springboot.data.dao.repository.ProductRepository;
import com.hana.springboot.data.domain.dto.product.ProductFileSaveDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.entity.Product;
import com.hana.springboot.data.service.ProductFileService;
import com.hana.springboot.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductFileService productFileService;
    @Override
    @Transactional
    public Product addProduct(ProductSaveDto dto) {

        dto.setProductCode(dto.getMemberCode());
        productFileService.saveProductImage(dto.getAttachFile(), dto.getProductCode());


        Product product = dto.toEntity();
        product.isVisibleTrue();
        product.isDeleteFalse();
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }
}
