package com.sapo.ex8_spring_security.model.dto.request;

import com.sapo.ex8_spring_security.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@AllArgsConstructor
public class CategoryRequest {

    @NotNull
    private String name;
    @NotNull
    private String description;



    public CategoryRequest(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();

    }
    public Category toEntity()
    {
        Category category=new Category();
        category.setName(getName());
        category.setDescription(getDescription());
        return category;
    }
}
