package com.example.ex05_db_springboot.services.impl;

import com.example.ex05_db_springboot.model.entity.v2.Category;
import com.example.ex05_db_springboot.model.entity.v2.Storage;
import com.example.ex05_db_springboot.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImp implements StorageService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Storage> getAll() {
        String query = "select * from storage";
        List<Storage> list = jdbcTemplate.query(query, new BeanPropertyRowMapper(Storage.class));
        return list;
    }

    @Override
    public Storage getById(int id) {
        String query = "select * from storage where id=" + id;
        List<Storage> stores = jdbcTemplate.query(query, new BeanPropertyRowMapper(Storage.class));
        return stores.get(0);
    }

    @Override
    public Storage getByName(String name) {
        String query = "select * from storage where name = ?";
        List<Storage> list = (List<Storage>) jdbcTemplate.query(query, new BeanPropertyRowMapper(Storage.class), name);
        return list.get(0);

    }

    @Override
    public Storage deleteById(int id) {
        String query = "Delete from storage where  id=?;";
        int resule = jdbcTemplate.update(query, id);
        return getById(id);
    }

    @Override
    public Storage add(Storage object) {


        object.setCode(getNewCode());
        String query = "insert into storage(code,name,address) values (?,?,?);";
        int rs = jdbcTemplate.update(query, object.getCode(), object.getName(), object.getAddress());
        return getByName(object.getName());
    }

    @Override
    public Storage update(Storage object) {

        String query = "update storage  set name=?,address=? where id=?";
        int rs = jdbcTemplate.update(query, object.getName(), object.getAddress(), object.getId());


        return getById(object.getId());
    }

    @Override
    public String getNewCode() {
        String queryForID = "select * from  storage order by id asc";
        List<Category> list = jdbcTemplate.query(queryForID, new BeanPropertyRowMapper(Category.class));
        Integer id = list.get(0).getId();

        return "ST" + id;
    }

}
