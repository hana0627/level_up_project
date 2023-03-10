package com.hana.springboot.data.domain.dto.product;

import com.hana.springboot.data.domain.entity.Product;
import com.hana.springboot.data.domain.entity.ProductFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Access;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductFileSaveDto {
    public String productCode; // 상품코드
    public String originalFileName; //파일이름
    public String dbFileName; // 저장되는 파일이름
    public String filePath; // 파일저장경로
    public Boolean isVisible;
    public Boolean isDelete;

    public ProductFile toEntity() {
        return ProductFile.builder()
                .productCode(productCode)
                .originalFileName(originalFileName)
                .dbFileName(dbFileName)
                .filePath(filePath)
                .isVisible(isVisible)
                .isDelete(isDelete)
                .build();
    }

    public ProductFileSaveDto(ProductFile productFile) {
        this.productCode = productFile.getProductCode();
        this.originalFileName = productFile.getOriginalFileName();
        this.dbFileName = productFile.getDbFileName();
        this.filePath = productFile.getFilePath();
        this.isVisible = productFile.getIsVisible();
        this.isDelete = productFile.getIsDelete();
    }
}
