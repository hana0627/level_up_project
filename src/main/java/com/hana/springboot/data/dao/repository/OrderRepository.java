package com.hana.springboot.data.dao.repository;

import com.hana.springboot.data.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
