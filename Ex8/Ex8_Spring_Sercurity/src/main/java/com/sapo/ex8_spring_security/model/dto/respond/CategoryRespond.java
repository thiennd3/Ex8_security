package com.sapo.ex8_spring_security.model.dto.respond;

import com.sapo.ex8_spring_security.model.dto.request.CategoryRequest;
import com.sapo.ex8_spring_security.model.entity.Category;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryRespond extends CategoryRequest {
    private Integer id;
    private String code;

    private java.sql.Timestamp createAt;
    private java.sql.Timestamp updateAt;


    public CategoryRespond(Category category) {
        super(category);

        this.id=category.getId();
        this.code=category.getCode();
        this.createAt=category.getCreateAt();
        this.updateAt=category.getUpdateAt();

    }

}
