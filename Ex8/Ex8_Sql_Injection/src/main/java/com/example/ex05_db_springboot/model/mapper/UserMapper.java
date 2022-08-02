package com.example.ex05_db_springboot.model.mapper;

import com.example.ex05_db_springboot.model.entity.v2.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user= new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setMail(rs.getString("mail"));
        user.setRole(rs.getInt("role"));
        return user;
    }
}
