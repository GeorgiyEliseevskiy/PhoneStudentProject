package com.example.phonestudentproject.model.entity;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
public class DefaultSystemAttributes {

    @Column(
            name = "create_dttm",
            nullable = false,
            updatable = false
    )
    @CreatedDate
    private Date createDate;

    @Column(
            name = "modify_dttm",
            nullable = false
    )
    @LastModifiedDate
    private Date modifyDate;

    public Date getCreateDate() {
        return this.createDate != null ? (Date)this.createDate.clone() : null;
    }

    public void setCreateDate(Date createDate) {
        this.createDate =  createDate != null ? (Date)this.createDate.clone() : null;
    }

    public Date getModifyDate() {
        return this.modifyDate != null ? (Date)this.modifyDate.clone() : null;

    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate != null ? (Date)this.modifyDate.clone() : null;
    }
}
