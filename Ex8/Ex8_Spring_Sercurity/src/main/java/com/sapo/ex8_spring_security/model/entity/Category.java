package com.sapo.ex8_spring_security.model.entity;

import com.sapo.ex8_spring_security.model.dto.request.CategoryRequest;
import com.sapo.ex8_spring_security.model.dto.respond.CategoryRespond;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "create_at")
    private java.sql.Timestamp createAt;

    @Column(name = "update_at")
    private java.sql.Timestamp updateAt;

    public void mergeWith(CategoryRequest categoryRequest) {
        this.name = categoryRequest.getName();
        this.description = categoryRequest.getDescription();
    }

    public void setTimeForAdd() {
        this.createAt = Timestamp.valueOf(LocalDateTime.now());
        this.updateAt = this.createAt;
    }
    public void setTimeForUpdate() {
        this.updateAt = Timestamp.valueOf(LocalDateTime.now());;
    }
    public CategoryRespond toResponeDTO()
    {
        return new CategoryRespond(this);
    }



}
