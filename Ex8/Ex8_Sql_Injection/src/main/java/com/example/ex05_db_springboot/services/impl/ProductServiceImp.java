package com.example.ex05_db_springboot.services.impl;

import com.example.ex05_db_springboot.model.entity.v2.Category;
import com.example.ex05_db_springboot.model.entity.v2.Product;
import com.example.ex05_db_springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // lấy tấy cả các sả phẩm
    @Override
    public List<Product> getAll() {
        String query = "select * from product";
        List<Product> list = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class));
        return list;

    }

    // lấy sản phẩm bằng id
    @Override
    public Product getById(int id) {
        String query = "select * from product where id=?";
        List<Product> list = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class), id);

        if (list.isEmpty())
            return null;

        return list.get(0);
    }

    @Override
    public Product getByName(String name) {
        String query = "select * from product where name =?;";
        List<Product> list = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class), name);
        return list.get(0);
    }

    @Override
    public Product deleteById(int id) {
        String query = "delete  from product where id =?;";
        int rs = jdbcTemplate.update(query, id);

        return getById(id);
    }

    @Override
    public Product add(Product object) {
        object.setCode(getNewCode());
        String query = "insert into product(code,name,category_id,storage_id,description,quantity,cost)  values(?,?,?,?,?,?,?);";
        int rs = jdbcTemplate.update(query, object.getCode(),object.getName(), object.getCategoryId(), object.getStorageId(), object.getDescription(), object.getQuantity(), object.getCost());

        return getByName(object.getName());

    }

    @Override
    public Product update(Product object) {


        String query = "update product set name=?,category_id=?, ma_kho=?, description=?,cost=?,quantity=?,sale_number=?,ngay_sua= ? where id=?";
        int rs = jdbcTemplate.update(query, object.getName(), object.getCategoryId(), object.getStorageId(), object.getDescription(), object.getCost(), object.getQuantity(), object.getSaleNumber(), java.time.LocalDateTime.now(), object.getId());

        return getById(object.getId());

    }

    @Override
    public String getNewCode() {
        String queryForID = "select * from  producr order by id asc";
        List<Category> list = jdbcTemplate.query(queryForID,new BeanPropertyRowMapper(Category.class));
        Integer id= list.get(0).getId();

        return"P"+id;
    }

}
