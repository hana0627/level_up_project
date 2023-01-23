package com.hana.springboot.data.domain.eunmClass;

import lombok.Getter;

@Getter
public enum MemberStatus {
    X("NULL"),
    ADMIN("관리자"),
    USER("사용자");

    private String dbValue;

    MemberStatus(String dbValue) {
        this.dbValue = dbValue;
    }
}
