package com.hana.springboot.data.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ProductFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15)
    private String productCode; // 상품코드
    private String originalFileName; //파일이름
    private String dbFileName; // 저장되는 파일이름
    private String filePath; // 파일저장경로
}
