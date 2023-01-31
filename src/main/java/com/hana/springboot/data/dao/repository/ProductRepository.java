package com.hana.springboot.data.dao.repository;

import com.hana.springboot.data.domain.entity.Member;
import com.hana.springboot.data.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
