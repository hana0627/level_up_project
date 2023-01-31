package com.hana.springboot.data.dao.repository;

import com.hana.springboot.data.domain.entity.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {
}
