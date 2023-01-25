package com.hana.springboot.data.dao.repository;

import com.hana.springboot.data.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByLoginIdAndIsVisibleAndIsDelete(String loginId, boolean isVisible, boolean isDelete);

    List<Member> findAllByIsVisibleAndIsDelete(boolean isVisible, boolean isDelete);
}
