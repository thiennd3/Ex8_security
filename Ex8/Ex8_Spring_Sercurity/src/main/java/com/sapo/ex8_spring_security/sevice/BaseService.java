package com.sapo.ex8_spring_security.sevice;

import java.util.List;

public interface BaseService<T> {


    List<T> getAll(String name, Integer page,Integer size);
    public String getNewCode();
    public T update(Integer id ,T entity);
    public Integer delete (Integer id);
    public T add(T entity);
}
