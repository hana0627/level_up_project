package com.hana.springboot.data.service;

import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductSaveDto dto);

    Page<ProductListDto> findAll(String memberCode, Pageable request);

}
