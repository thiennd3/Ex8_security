package com.example.ex05_db_springboot.services;

import com.example.ex05_db_springboot.model.entity.v2.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface UserService {



    User getUser(String username,String password);

    User getUserWithProtect(String username, String password);
}
