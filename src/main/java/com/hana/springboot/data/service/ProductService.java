package com.hana.springboot.data.service;

import com.hana.springboot.data.domain.dto.product.ProductListDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductSaveDto dto);

    List<ProductListDto> findAll(String memberCode);

}
