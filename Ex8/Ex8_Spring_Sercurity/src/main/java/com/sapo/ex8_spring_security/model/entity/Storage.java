package com.sapo.ex8_spring_security.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "create_at")
    private java.sql.Timestamp createAt;

    @Column(name = "update_at")
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
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
