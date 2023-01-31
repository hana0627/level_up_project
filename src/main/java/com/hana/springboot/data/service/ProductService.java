package com.hana.springboot.data.service;

import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.entity.Product;

public interface ProductService {

    Product addProduct(ProductSaveDto dto);

}
