package com.hana.springboot.data.dao.repository;

import com.hana.springboot.data.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
