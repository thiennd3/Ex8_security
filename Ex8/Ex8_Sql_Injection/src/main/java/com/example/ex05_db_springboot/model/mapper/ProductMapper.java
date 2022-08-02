package com.example.ex05_db_springboot.model.mapper;

import com.example.ex05_db_springboot.model.entity.v2.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {


    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product=new Product();
        product.setId(rs.getInt("id"));
        product.setCode(rs.getString("code"));
        product.setName(rs.getString("name"));
        product.setCost(rs.getBigDecimal("cost"));
        product.setImage(rs.getString("image"));
        product.setDescription(rs.getString("description"));
        product.setQuantity(rs.getInt("quantity"));
        product.setSaleNumber(rs.getInt("sale_number"));
        product.setCreateAt(rs.getTimestamp("create_at"));
        product.setUpdateAt(rs.getTimestamp("update_at"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setStorageId(rs.getInt("ma_kho"));
        return product;
    }

}
