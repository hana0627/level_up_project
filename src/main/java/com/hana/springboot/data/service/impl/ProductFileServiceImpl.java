package com.hana.springboot.data.service.impl;

import com.hana.springboot.data.dao.repository.ProductFileRepository;
import com.hana.springboot.data.domain.dto.product.ProductFileSaveDto;
import com.hana.springboot.data.domain.dto.product.ProductSaveDto;
import com.hana.springboot.data.domain.entity.ProductFile;
import com.hana.springboot.data.service.ProductFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductFileServiceImpl implements ProductFileService {

    private final ProductFileRepository productFileRepository;

    @Value("${file.dir}")
    private String fileDir;
    
    public String getFullPath(String filename) {
        return fileDir + filename;
    }
    @Override
    public void saveProductImage(MultipartFile multipartFile, String productCode) {

        //경로가 엾으면 생성
        if (!new File(fileDir).exists()) {
            try {
                new File(fileDir).mkdir();
            } catch (RuntimeException e) {
                e.getStackTrace();
            }
        }


        String originalFilename = multipartFile.getOriginalFilename();

        String ext = extractExt(originalFilename);

        String uuid = UUID.randomUUID().toString();
        String dbFileName = uuid + "." + ext;

        String fullPath = getFullPath(dbFileName);
        try {
            multipartFile.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProductFileSaveDto saveDto = new ProductFileSaveDto();
        saveDto
                .setProductCode(productCode)
                .setFilePath(fullPath)
                .setOriginalFileName(originalFilename)
                .setDbFileName(dbFileName)
                .setIsVisible(true)
                .setIsDelete(false);

        ProductFile productFile = saveDto.toEntity();
        productFileRepository.save(productFile);


    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }


}
