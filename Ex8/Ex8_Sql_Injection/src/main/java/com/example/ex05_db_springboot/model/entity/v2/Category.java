package com.example.ex05_db_springboot.model.entity.v2;

import javax.persistence.*;


public class Category {

    private Integer id;

    private String code;

    private String name;

    private String description;

    private java.sql.Timestamp createAt;

    private java.sql.Timestamp updateAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Timestamp getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(java.sql.Timestamp createAt) {
        this.createAt = createAt;
    }

    public java.sql.Timestamp getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(java.sql.Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
