package com.hana.springboot.data.domain.baseEntity;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; //생성시각

    @LastModifiedDate
    private LocalDateTime updatedAt; //업데이트 시각

    private Boolean isVisible; // 화면에 보여줄지 여부

    private Boolean isDelete; // 삭제된 엔티티인지 여부


    @PrePersist
    private void setIsVisibleTrueAndIsDeleteFalse() {
        this.isVisible=true;
        this.isDelete=false;
    }

    public void isVisibleFalse() {
        this.isVisible = false;
    }
    public void isDeleteTrue() {
        this.isDelete = true;
    }

    public void isVisibleTrue(){
        this.isVisible = true;
    }
    public void isDeleteFalse(){
        this.isDelete = false;
    }
}
