package com.hana.springboot.data.dao.repository;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByMemberCodeAndIsVisibleAndIsDelete(String memberCode, boolean isVisible, boolean isDelete);

    Optional<Product> findByProductCodeAndIsVisibleAndIsDelete(String productCode, boolean isVisible, boolean isDelete);
}
