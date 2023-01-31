package com.hana.springboot.data.domain.eunmClass;

import lombok.Getter;

@Getter
public enum OrderStatus {
    X("NULL"),
    ORDER("입금대기"),
    PAYED("결재완료"),
    DELIVERYREADY("배송준비중"),
    DELIVERYING("배송중"),
    DELIVERYFINISH("배송완료"),
    CANCEL("취소신청"),
    CANCELCONFIRM("취소접수"),
    CANCELING("취소회수중"),
    CANCELFINISH("취소완료");

    private String dbValue;

    OrderStatus(String dbValue) {
        this.dbValue = dbValue;
    }
}
