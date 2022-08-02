package com.example.ex05_db_springboot.services;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BaseService<T> {
    List<T> getAll();
    T getById(int id);
    T getByName(String name);
    T deleteById(int id);

    T add(T object);
    T update(T object);
    String getNewCode();


}
