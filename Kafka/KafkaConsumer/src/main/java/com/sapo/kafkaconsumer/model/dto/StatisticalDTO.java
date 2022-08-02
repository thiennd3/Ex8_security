package com.sapo.kafkaconsumer.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatisticalDTO {
    private Integer id;
    private Integer storageId;
    private Integer number;
    private java.sql.Timestamp createAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStorageId() {
        return this.storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public java.sql.Timestamp getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(java.sql.Timestamp createAt) {
        this.createAt = createAt;
    }
}
