package com.hana.springboot.data.service;

import com.hana.springboot.data.domain.dto.product.ProductDetailDto;
import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.dto.product.ProductUpdateDto;
import com.hana.springboot.data.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product addProduct(String memberCode, ProductSaveDto dto);

    Page<ProductListDto> findAll(String memberCode, Pageable pageable);

    ProductDetailDto findOne(String productCode);

    Long updateProduct(String memberCode, ProductUpdateDto dto);
}
