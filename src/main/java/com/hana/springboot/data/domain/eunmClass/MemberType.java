package com.hana.springboot.data.domain.eunmClass;

import lombok.Getter;

@Getter
public enum MemberType {
    X("NULL"),
    SELLER("판매자"),
    USER("사용자");

    private String dbValue;

    MemberType(String dbValue) {
        this.dbValue = dbValue;
    }
}
