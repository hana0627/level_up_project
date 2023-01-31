package com.hana.springboot.data.service;

import com.hana.springboot.data.domain.dto.product.ProductFileSaveDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductFileService {
    void saveProductImage(MultipartFile multipartFile, String productCode);
}
