package com.example.ex05_db_springboot.model.mapper;

import com.example.ex05_db_springboot.model.entity.v2.Storage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StorageMapper implements RowMapper<Storage> {

    @Override
    public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Storage storage =new Storage();
        storage.setId(rs.getInt("id"));
        storage.setAddress(rs.getString("address"));
        storage.setCode(rs.getString("code"));
        storage.setCreateAt(rs.getTimestamp("create_at"));
        storage.setUpdateAt(rs.getTimestamp("create_at"));
        return storage;

    }



}
