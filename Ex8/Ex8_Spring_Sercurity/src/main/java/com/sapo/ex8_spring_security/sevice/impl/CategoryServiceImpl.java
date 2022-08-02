package com.sapo.ex8_spring_security.sevice.impl;

import com.sapo.ex8_spring_security.model.entity.Category;
import com.sapo.ex8_spring_security.model.entity.Product;
import com.sapo.ex8_spring_security.repository.CategoryRepository;
import com.sapo.ex8_spring_security.sevice.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepos;


    @Override
    public List<Category> getAll(String name, Integer page, Integer size) {
        return categoryRepos.getByName(name, PageRequest.of(page, size, Sort.Direction.ASC)).toList();
    }

    @Override
    public String getNewCode() {
        String newCode = "C";
        Category category = categoryRepos.getTopById();
        newCode = newCode + String.valueOf(category.getId() + 1);
        return newCode;
    }

    @Override
    public Category update(Integer id, Category entity) {

        Category category = categoryRepos.findById(id).orElseThrow(()->{   throw new RuntimeException("Id is not exist"); });


            category.setDescription(entity.getDescription());
            category.setUpdateAt(Timestamp.valueOf(LocalDateTime.now()));
            category.setName(entity.getName());

            return categoryRepos.save(category);

    }

    @Override
    public Integer delete(Integer id) {
        if (!categoryRepos.existsById(id)) {
            throw new RuntimeException("Id is not exist");
        } else {
            Integer rs = categoryRepos.deleteCategoriesById(id);
            return id;

        }
    }

    @Override
    public Category add(Category entity) {
        // thêm ngày tạo
        entity.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        entity.setUpdateAt(entity.getCreateAt());
        // tạo mã mới cho danh mục
        entity.setCode(getNewCode());

        return categoryRepos.save(entity);
    }
}
