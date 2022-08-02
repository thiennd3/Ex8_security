package com.example.ex05_db_springboot.model.mapper;

import com.example.ex05_db_springboot.model.entity.v2.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoriesMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category=new Category();
        category.setId(rs.getInt("id"));
        category.setDescription(rs.getString("description"));
        category.setName(rs.getString("name"));
        category.setCreateAt(rs.getTimestamp("create_at"));
        category.setUpdateAt(rs.getTimestamp("update_at"));
        category.setCode(rs.getString("code"));

        return category;
    }



}
