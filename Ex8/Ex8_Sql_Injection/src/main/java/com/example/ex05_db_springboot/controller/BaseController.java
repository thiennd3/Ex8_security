package com.example.ex05_db_springboot.controller;

import java.util.List;

public interface BaseController<T> {
    void showAll();
    void show(T item);

    void addNew();
    void update();
    void delete();
}
