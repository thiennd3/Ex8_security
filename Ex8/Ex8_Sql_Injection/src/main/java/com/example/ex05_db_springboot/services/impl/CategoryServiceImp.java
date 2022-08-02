package com.example.ex05_db_springboot.services.impl;

import com.example.ex05_db_springboot.model.entity.v2.Category;
import com.example.ex05_db_springboot.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class CategoryServiceImp implements CategoryService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Category> getAll() {
        String query = "select * from category";
        List<Category> list = jdbcTemplate.query(query, new BeanPropertyRowMapper(Category.class));
        return list;
    }

    @Override
    public Category getById(int id) {
        String query = "select * from category where id=?";
        List<Category> categories = jdbcTemplate.query(query, new BeanPropertyRowMapper(Category.class), id);
        return categories.get(0);
    }

    @Override
    public Category getByName(String name) {
        String query = "select * from category where name = ?";
        List<Category> list = jdbcTemplate.query(query, new BeanPropertyRowMapper(Category.class), name);
        return list.get(0);
    }

    @Override
    public Category deleteById(int id) {

        String query = " call delete_category_by_id(?)";
        jdbcTemplate.update(query, id);

        return null;
    }

    @Override
    public Category add(Category object) {

            object.setCode(getNewCode());
        String query = "insert into category(code,name,description)  values(?,?,?);";
        int rs = jdbcTemplate.update(query,object.getCode() ,object.getName(), object.getDescription());

        return getByName(object.getName());
    }

    @Override
    public Category update(Category object) {
        String query = "update loai_danh_muc  " +
                " set name=?,description=?,update_at=?" +
                "where id=?";
        int rs = jdbcTemplate.update(query, object.getName(), object.getDescription(), java.time.LocalDateTime.now(), object.getId());

        return getById(object.getId());
    }

    @Override
    public String getNewCode() {
        String queryForID = "select * from  category order by id asc";
        List<Category> list = jdbcTemplate.query(queryForID,new BeanPropertyRowMapper(Category.class));
        Integer id= list.get(0).getId();

        return "C"+id;
    }

}
