package com.example.ex05_db_springboot.services.impl;

import com.example.ex05_db_springboot.model.entity.v2.User;
import com.example.ex05_db_springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public User getUser(String username, String password) {

        String query = "select * from user where username='" + username + "' and password = '" + password + "'";
        try {
            List<User> user = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
            return user.get(0);
        } catch (Exception e) {
            return null;
        }
    }

        @Override
        public User getUserWithProtect(String username, String password) {

            String query = "select * from user where username=? and password =?";
            try{

                List<User> user = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class),username,password);
                return user.get(0);
            }catch (Exception e){
                return null;
            }

    }
}
